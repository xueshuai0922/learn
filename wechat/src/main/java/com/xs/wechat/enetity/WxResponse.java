package com.xs.wechat.enetity;

/**
 * @author xueshuai
 * @date 2022/4/5 19:00
 * @description 企业微信返回实体类
 */
public class WxResponse {

//    {
//        "errcode" : 0,
//            "errmsg" : "ok",
//            "invaliduser" : "userid1|userid2",
//            "invalidparty" : "partyid1|partyid2",
//            "invalidtag": "tagid1|tagid2",
//            "msgid": "xxxx",
//            "response_code": "xyzxyz"
//    }
    private int errcode;
    private String errmsg;
    private String invaliduser;
    private String invalidparty;
    private String invalidtag;
    private String msgid;
    private String response_code;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getInvaliduser() {
        return invaliduser;
    }

    public void setInvaliduser(String invaliduser) {
        this.invaliduser = invaliduser;
    }

    public String getInvalidparty() {
        return invalidparty;
    }

    public void setInvalidparty(String invalidparty) {
        this.invalidparty = invalidparty;
    }

    public String getInvalidtag() {
        return invalidtag;
    }

    public void setInvalidtag(String invalidtag) {
        this.invalidtag = invalidtag;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    @Override
    public String toString() {
        return "WxResponse{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", invaliduser='" + invaliduser + '\'' +
                ", invalidparty='" + invalidparty + '\'' +
                ", invalidtag='" + invalidtag + '\'' +
                ", msgid='" + msgid + '\'' +
                ", response_code='" + response_code + '\'' +
                '}';
    }
}
