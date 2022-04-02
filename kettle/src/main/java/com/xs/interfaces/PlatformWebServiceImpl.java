package com.xs.interfaces;

/**
 * @author xueshuai
 * @date 2021/8/25 8:57
 * @description
 */

import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://interfaces.xs.com")
@Component
public class PlatformWebServiceImpl implements PlatformWebService {

    @Override
    public String getList(@WebParam(name = "barcode")String barcode) {
        return "<root><code>1</code><msg>同步成功</msg></root>";
    }

    @Override
    public String auth(  String access) {
//
        return " {\"code\":\"12122\",\"token\":\"sssssss\"}";
    }

}