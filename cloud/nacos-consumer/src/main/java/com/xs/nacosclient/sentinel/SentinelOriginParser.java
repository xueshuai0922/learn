package com.xs.nacosclient.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xueshuai
 * @date 2021/6/16 8:15
 * @description sentinel鉴权规则解析
 */
@Component
public class SentinelOriginParser  implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String parameter = request.getParameter("token");
        if(StringUtils.isEmpty(parameter)){
            throw  new RuntimeException("调用方参数未绑定");
        }
        return parameter;
    }
}
