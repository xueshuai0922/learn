package com.xs.mybatis.login.controller;


import com.xs.mybatis.common.CommonResult;
import com.xs.mybatis.login.entity.User;
import com.xs.mybatis.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xueshuai
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/login/user")
public class UserController {
        @Autowired
        private IUserService userService;


        @PostMapping("/select")
        public CommonResult select(){
            return userService.select();
        }

        @PostMapping("/selectOne/{id}")
        public CommonResult select(@PathVariable String id ){
                return userService.selectOne(id);
        }

        @PostMapping("/selectChoose")
        public CommonResult selectChoose(@RequestBody User user ){
                return userService.selectChoose(user);
        }

        @PostMapping("/selectByPage")
        public CommonResult selectByPage(){
                return userService.selectByPage();
        }

        @PostMapping("/deleteBatch/{ids}")
        public CommonResult deleteBatch( @PathVariable String ids){
                return userService.deleteBatch(ids);
        }
}
