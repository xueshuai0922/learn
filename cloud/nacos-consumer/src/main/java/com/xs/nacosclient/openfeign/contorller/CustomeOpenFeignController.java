package com.xs.nacosclient.openfeign.contorller;

import com.xs.nacosclient.openfeign.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueshuai
 * @date 2021/5/11 9:28
 * @description
 */
@RestController
public class CustomeOpenFeignController {


    @Autowired(required = false)
    private CustomService customService;

    @GetMapping("/custom/openfeign")
    public Object customeFeign() {
//        return "";
        return customService.getPort();
    }


}
