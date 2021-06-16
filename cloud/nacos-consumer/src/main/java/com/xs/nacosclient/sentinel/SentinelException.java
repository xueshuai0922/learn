package com.xs.nacosclient.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.xs.nacosclient.common.CommonResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author xueshuai
 * @date 2021/6/15 21:25
 * @description sentinel 自定义异常返回
 */
@Component
public class SentinelException  implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        CommonResult commonResult = null;

        if(e instanceof  FlowException){
            commonResult= commonResult.builder().msg("不好意思，【"+request.getRequestURI()+"】被限流了").build();
        }else  if (e instanceof DegradeException){
            commonResult= commonResult.builder().msg("不好意思，【"+request.getRequestURI()+"】被熔断了").build();
        }else  if (e instanceof ParamFlowException){
            commonResult= commonResult.builder().msg("不好意思，【"+request.getRequestURI()+"】被热点规则限制了").build();
        }else  if (e instanceof AuthorityException){
            commonResult= commonResult.builder().msg("不好意思，【"+request.getRequestURI()+"】被鉴权规则限制了").build();
        }else  if (e instanceof SystemBlockException){
            commonResult= commonResult.builder().msg("不好意思，【"+request.getRequestURI()+"】被系统规则限制了").build();
        }
        response.setStatus(500);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        Object json = JSON.toJSON(commonResult);
        writer.print(json);
        writer.flush();
        writer.close();


    }
}
