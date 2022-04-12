package com.xs.wechat.compony;

import com.xs.wechat.enetity.SendPara;
import com.xs.wechat.enetity.WxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author xueshuai
 * @date 2022/4/5 18:25
 * @description 企业微信对接 发送消息
 */

@RestController
public class CompanyWeChat {

    @Autowired
    private RestTemplate restTemplate;

    private final String wechatUri="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";

    private final String accessToken ="L2RbQZd8nyu0kJsUkv8aBdzPyNdaydTKh4up8qe9sSPZLVAMtuwiHGEty7u0B28OmdWH1JIygoUYMCUKpSq9-V9hTuN-A5QCde3d8eU1YR25yuwMggbU51PZWHteOtOrN3Eu2c9vJ60-U9GCk2SfBwHHIDKn3CmuB-ZY8ab6wc064J14y4d9rfTtDFOxlhFsshTWXU8nTjFIQXE-69NbYw";
    //企业应用id
    private final long  agentId= 1_000_002;

    @GetMapping("/SendWxMsg")
    public  void  SendWxMsg(){
        SendPara sendPara = new SendPara();
        sendPara.setTouser("XueShuai");
        sendPara.setMsgtype("markdown");
        sendPara.setAgentid(agentId);
        HashMap<String, String> markMap = new HashMap<>();
        String markText="您的会议室已经预定，稍后会同步到`邮箱` \n" +
                "                                >**事项详情** \n" +
                "                                >事　项：<font color=\"info\">开会</font> \n" +
                "                                >组织者：@xueshuai \n" +
                "                                >参与者：@xueshuai\n" +
                "                                > \n" +
                "                                >会议室：<font color=\"info\">西安 301</font> \n" +
                "                                >日　期：<font color=\"warning\">2022年5月18日</font> \n" +
                "                                >时　间：<font color=\"comment\">上午9:00-11:00</font> \n" +
                "                                > \n" +
                "                                >请准时参加会议。 \n" +
                "                                > \n" +
                "                                >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)";
        markMap.put("content",markText);

        sendPara.setMarkdown(markMap);
        WxResponse wxResponse = restTemplate.postForObject(wechatUri+accessToken, sendPara, WxResponse.class);
        System.out.println(wxResponse);
    }


}
