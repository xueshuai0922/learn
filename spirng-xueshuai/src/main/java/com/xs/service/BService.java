package com.xs.service;

import com.xs.spring.Autowire;
import com.xs.spring.Component;

/**
 * @author xueshuai
 * @date 2022/4/23 16:14
 * @description
 */
@Component
public class BService {
    @Autowire
    private AService AService;
}
