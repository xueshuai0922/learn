package com.xs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2022/3/26 12:42
 * @description
 */


@Component
@ConfigurationProperties(value = "minio")
public class MinioProperties {
    //需要给默认值
    private String endpoint="127.0.0.1";
    private String accessKey="amdin";
    private String secretKey="admin";
    private String bucketName="xueshuai";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getFilHost() {
        return bucketName;
    }

    public void setFilHost(String bucketName) {
        this.bucketName = bucketName;
    }
}
