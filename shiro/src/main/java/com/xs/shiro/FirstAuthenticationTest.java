package com.xs.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author xueshuai
 * @date 2021/1/6 9:28
 * @description shiro 不结合boot进行的用户认证和鉴权测试
 */
public class FirstAuthenticationTest {

    public static void main(String[] args) {
        //1.securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.创建一个ini的realm，并注册到manager中
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        securityManager.setRealm(iniRealm);

        //3.把manger给工具类
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("xueshuais", "123");
        //4.工具类进行验证
        try {
            currentUser.login(token);

            System.out.println("认证成功");
            /*
             * 权限的认证：角色权限认证role、操作权限
             *
             * */


            //1)先判断是否有该角色权限:hasRole  checkRole 返回不同类型的exception
            currentUser.checkRole("roleone");
            System.out.println("你有roleone的角色权限");
            //2) 后判断是否有操作权限
            currentUser.checkPermission("user:say");
            System.out.println("你有say的权限");

//            if(currentUser.hasRole("admin")){
//                System.out.println("有操作权限：admin");
//
//            }else {
//                System.out.println("无admin的操作权限");
//            }

        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            System.out.println("用户错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
