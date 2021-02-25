package com.xs.shiro.shiro;

import com.xs.shiro.filter.JWTFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSentinelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xueshuai
 * @date 2021/1/6 9:22
 * @description shiro的配置类： SecurityManager、CustomerRealm、
 */
@Configuration
public class ShiroConfig {

    private final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Value("${spring.redis.sentinel.nodes}")
    private String host;

    /**
     * 注入自定义realm，shiro最后执行认证，鉴权的类。两个方法doGetAuthorizationInfo  doGetAuthenticationInfo
     *
     * @return
     */
    @Bean
    public CustomerRealm customerRealm(CacheManager cacheManager) {
        logger.info("进入customerRealm");
        CustomerRealm customerRealm = new CustomerRealm();
        //修改 凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setHashAlgorithmName("md5");
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        //缓存处理

        //增加
        customerRealm.setCacheManager(cacheManager);
        //缓存生效
        customerRealm.setCachingEnabled(true);
        //登录缓存生效
        customerRealm.setAuthenticationCachingEnabled(true);
        //认证缓存生效
        customerRealm.setAuthorizationCachingEnabled(true);


        return customerRealm;
    }


    public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

        @Override
        public Subject createSubject(SubjectContext context) {
            //不创建session
            context.setSessionCreationEnabled(false);
            return super.createSubject(context);
        }
    }


    /**
     * 注入安全管理器
     *
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(CustomerRealm customerRealm) {
        logger.info("进入securityManager");
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        //安全管理器注入realm
        defaultSecurityManager.setRealm(customerRealm);
        //todo session 去掉，下次那请求中的token进行验证


        return defaultSecurityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }

    /**
     * shiro实现的大前提就是filter 过滤器来过滤所有的请求
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        logger.info("进入shiroFilterFactoryBean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给shiro过滤工厂类设置安全管理类
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        HashMap<String, Filter> filterHashMap = new HashMap<String, Filter>();
        filterHashMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterHashMap);


//        公共资源和业务资源进行过滤
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/register", "anon");
        filterMap.put("/**", "jwt");//authc 过滤，anon匿名访问（不进行过滤）
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置没有权限后 转向的地址
        shiroFilterFactoryBean.setSuccessUrl("localhost:8888");
        //设置登录地址
        shiroFilterFactoryBean.setLoginUrl("localhost:8888/login");

        return shiroFilterFactoryBean;
    }


    @Bean
    public CacheManager cacheManager() {
        RedisSentinelManager redisSentinelManager = new RedisSentinelManager();
        redisSentinelManager.setHost(host);
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisSentinelManager);
        return redisCacheManager;
    }


}
