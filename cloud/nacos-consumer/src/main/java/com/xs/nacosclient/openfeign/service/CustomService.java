package com.xs.nacosclient.openfeign.service;

import com.xs.nacosclient.openfeign.annotation.XsOpenFeign;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xueshuai
 * @date 2021/5/11 9:32
 * @description
 */
@XsOpenFeign(name = "/naocs-provider")
public interface CustomService {

    @GetMapping("/getPort")
    String getPort();
}
