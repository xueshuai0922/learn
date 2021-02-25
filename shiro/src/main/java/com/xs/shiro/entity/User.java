package com.xs.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xueshuai
 * @date 2021/1/8 13:47
 * @description 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String userid;
    private String username;
    private String password;
    private String salt;

}
