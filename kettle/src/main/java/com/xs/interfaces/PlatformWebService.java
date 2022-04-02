package com.xs.interfaces;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author xueshuai
 * @date 2021/8/25 8:56
 * @description 流转平台webservice接口
 */
@WebService(targetNamespace = "http://interfaces.xs.com")
public interface PlatformWebService {

    @WebMethod(operationName="getList")
    String getList(@WebParam(name = "barcode") String barcode);


    @WebMethod(operationName="auth")
    String auth(@WebParam(name = "access",targetNamespace = "http://interfaces.xs.com") String access);
}
