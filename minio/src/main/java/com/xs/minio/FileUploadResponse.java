package com.xs.minio;

/**
 * @author xueshuai
 * @date 2022/3/26 16:47
 * @description
 */


public class FileUploadResponse {
    private String urlHttp;

    private String urlPath;


    public FileUploadResponse(String urlHttp, String urlPath) {
        this.urlHttp = urlHttp;
        this.urlPath = urlPath;
    }

    public String getUrlHttp() {
        return urlHttp;
    }

    public void setUrlHttp(String urlHttp) {
        this.urlHttp = urlHttp;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
