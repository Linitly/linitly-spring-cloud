package com.linitly.feign.client;

import com.linitly.feign.client.dao.TestMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// 开启刷新，需要在使用的每个类上面添加此注解，无法刷新数据库等信息
// 热更新会对两类 Bean 进行配置刷新，一类是使用了 @ConfigurationProperties 的对象，另一类是使用了 @RefreshScope 的对象
@RefreshScope
@RestController
@EnableHystrixDashboard
@SpringCloudApplication
@ComponentScan(value = "com.linitly")
@EnableFeignClients(value = "com.linitly")
@ServletComponentScan(value = "com.linitly")
@MapperScan(basePackages = "com.linitly.feign.client.dao")
public class FeignClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Value("${foo.foo}")
    private String foo;

    @Autowired
    private TestMapper testMapper;

    @GetMapping(value = "/hi")
    public String hi() {
        System.out.println(testMapper.findById());
        return foo;
    }
}
