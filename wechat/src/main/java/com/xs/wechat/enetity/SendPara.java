package com.xs.wechat.enetity;

import java.util.HashMap;

/**
 * @author xueshuai
 * @date 2022/4/5 18:31
 * @description 企业微信发送消息参数实体类
 */
public class SendPara {

//    {
//        "touser" : "UserID1|UserID2|UserID3",
//            "toparty" : "PartyID1|PartyID2",
//            "totag" : "TagID1 | TagID2",
//            "msgtype": "markdown",
//            "agentid" : 1,
//            "markdown": {
//        "content": "您的会议室已经预定，稍后会同步到`邮箱`
//                >**事项详情**
//                                >事　项：<font color=\"info\">开会</font>
//                >组织者：@miglioguan
//                                >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang
//                                >
//                                >会议室：<font color=\"info\">广州TIT 1楼 301</font>
//                >日　期：<font color=\"warning\">2018年5月18日</font>
//                >时　间：<font color=\"comment\">上午9:00-11:00</font>
//                >
//                                >请准时参加会议。
//                                >
//                                >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)"
//    },
//        "enable_duplicate_check": 0,
//            "duplicate_check_interval": 1800
//    }
    //是否必须：否 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
    private String  touser;
    //是否必须：否 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
    private String toparty;
    private String totag;	//否	标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
    private String msgtype;	//	是	消息类型，此时固定为：textcard
    private long agentid;	//	是	企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
    private HashMap markdown;	//	是
    private int enable_duplicate_check;	//	否	表示是否开启重复消息检查，0表示否，1表示是，默认0
    private int duplicate_check_interval;	//	否	表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public long getAgentid() {
        return agentid;
    }

    public void setAgentid(long agentid) {
        this.agentid = agentid;
    }

    public HashMap getMarkdown() {
        return markdown;
    }

    public void setMarkdown(HashMap markdown) {
        this.markdown = markdown;
    }

    public int getEnable_duplicate_check() {
        return enable_duplicate_check;
    }

    public void setEnable_duplicate_check(int enable_duplicate_check) {
        this.enable_duplicate_check = enable_duplicate_check;
    }

    public int getDuplicate_check_interval() {
        return duplicate_check_interval;
    }

    public void setDuplicate_check_interval(int duplicate_check_interval) {
        this.duplicate_check_interval = duplicate_check_interval;
    }
}
