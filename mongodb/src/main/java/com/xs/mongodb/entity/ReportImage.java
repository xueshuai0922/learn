package com.xs.mongodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author xueshuai
 * @date 2022/4/5 11:46
 * @description
 */
@Document
public class ReportImage {
//    @Id
//    private long id;
    @Field
    private long reportNo;
    @Field
    private String srcHosCode;
    @Field
    private String imgBase64;


//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public long getReportNo() {
        return reportNo;
    }

    public void setReportNo(long reportNo) {
        this.reportNo = reportNo;
    }

    public String getSrcHosCode() {
        return srcHosCode;
    }

    public void setSrcHosCode(String srcHosCode) {
        this.srcHosCode = srcHosCode;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }
}
