package com.xs.consumer.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @author xueshuai
 * @date 2021/5/5 18:19
 * @description  actuate 健康状态获取
 */

@Service
public class HealthService implements HealthIndicator {


    //这里一定要进行给默认值 true，不然注册中心获取不到健康状态，直接给down了
    private boolean status=true;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        if(status)
            return  new Health.Builder().up().build();
        return new Health.Builder().down().build();
    }
}
