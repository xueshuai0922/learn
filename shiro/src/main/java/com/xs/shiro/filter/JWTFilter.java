package com.xs.shiro.filter;


import com.xs.shiro.jwt.JWTToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


/**
 * @author xueshuai
 * @date 2021/1/13 16:38
 * @description jwt的filter 验证请求token
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    /**
     * 判断是否登录，如果登录返回true
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        Subject subject = SecurityUtils.getSubject();
        return null != subject && subject.isAuthenticated();

    }

    /**
     * 如果没有登录，则进行下面
     * 1、判断请求是否有token
     * 2.有token进行验证
     * 3.验证成功刷新过期时间
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1.检查请求头中是否含有token
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        //2. 如果客户端没有携带token，拦下请求
        if (null == token || "".equals(token)) {
            responseTokenError(response, "Token无效，您无权访问该接口");
            return false;
        }
        //3. 如果有，对进行进行token验证
        JWTToken jwtToken = new JWTToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            responseTokenError(response, e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 无需转发，直接返回Response信息 Token认证错误
     */
    private void responseTokenError(ServletResponse response, String msg) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
//            HashMap<String, Object> errorData = new HashMap<>();
//            errorData.put("errorCode", SystemCodeEnum.TOKEN_ERROR.getErrorCode());
//            errorData.put("errorMsg",SystemCodeEnum.TOKEN_ERROR.getErrorMsg());
//            ResponseBean<HashMap<String, Object>> result = ResponseBean.error(errorData);
//            String data = new Gson().toJson(result);
//            out.append(data);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }


}
