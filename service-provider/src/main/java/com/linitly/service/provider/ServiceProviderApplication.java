/**
 * @author: linxiunan
 * @date: 2019/8/13 13:58
 * @descrption
 **/
package com.linitly.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringCloudApplication
@EnableHystrixDashboard
@EnableFeignClients(value = "com.linitly")
public class ServiceProviderApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderApplication.class, args);
    }
}
