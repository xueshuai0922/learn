package com.xs.service;

import com.xs.spring.Autowire;
import com.xs.spring.Component;

/**
 * @author xueshuai
 * @date 2022/4/23 16:14
 * @description 模拟spring的循环依赖 a-->b   b--->a
 */
@Component
public class AService {
    @Autowire
    private BService BService;
}
