package com.xs.shiro;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@SpringBootTest
class ShiroApplicationTests {

    @Test
    void contextLoads() {
        HttpRequest get = HttpUtil.createGet("http://118.190.141.119:6379");
        get.execute();


    }

}
