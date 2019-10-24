/**
 * @author: linxiunan
 * @date: 2019/9/20 10:18
 * @descrption
 */
package com.linitly.feign.client.controller;

import com.linitly.feign.client.dao.TestMapper;
import com.linitly.feign.client.service.FeignTestService;
import com.linitly.service.provider.clients.ServiceProviderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@Slf4j
@RestController
public class FeignTestController {

    @Value("${foo.foo}")
    private String foo;

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private ServiceProviderClient serviceProviderClient;
    @Autowired
    private FeignTestService feignTestService;

    @GetMapping("/v1.0.1/test-feign")
    public String testFeign(@RequestParam Integer time) {
        System.out.println(testMapper.findById());
//        System.out.println("进入feign-client的测试超时时间方法");
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String result = serviceProviderClient.getMessage(time);
//        return result + ";my-foo:" + foo;
        return "my-foo:" + foo;
    }

    /**
     * @author linxiunan
     * @date 2019/9/26 14:34
     * @description 请求缓存功能，参考地址：https://www.cnblogs.com/hellxz/p/9056806.html
     * 使用时需在启动类上加上@EnableCircuitBreaker(这里消耗了半天时间)，并且初始化上下文对象，可以使用过滤器实现
     * 使用了@HystrixCommand注解，方法里面可以使用feign客户端调用，但是如果出错不会走feign的熔断，需要使用ribbon的方式写方法去解决异常情况
     * 使用请求缓存时，一般在一次请求中多次调用另一个服务的某个方法时使用请求缓存，实际使用中用到较少，可视情况而定，且如果使用请求缓存，建议完全使用ribbon的方式，
     * 不要使用ribbon和feign的结合使用
     */
    @GetMapping("/test-cache")
    public void testCache() {
        String test1 = feignTestService.cache(1);
        String test2 = feignTestService.cache(1);
        String test3 = feignTestService.cache(2);
        log.info("first is {}; second is {}; third is {}", test1, test2, test3);
        // 清除缓存
        feignTestService.cacheRemove(1);
        String test4 = feignTestService.cache(1);
        log.info("first is {}", test4);
    }

    @GetMapping("/test-request-merge")
    public void testRequestMerge() throws Exception {
        Future<String> future1 = feignTestService.findOne(1);
        Future<String> future2 = feignTestService.findOne(2);
        Future<String> future3 = feignTestService.findOne(3);

        log.info(future1.get());
        log.info(future2.get());
        log.info(future3.get());
    }

    @GetMapping("/v1.0.0/test-feign")
    public String test() {
        return "test-zuul-strip-prefix success";
    }
}
