/**
 * @author: linxiunan
 * @date: 2019/8/13 14:07
 * @descrption
 **/
package com.linitly.service.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class TestController {

    /**
     * 测试读取配置文件
     */
    @Value("${foo.foo}")
    private String foo;

    @GetMapping("/test")
    public String test() {
        return "foo:" + foo;
    }

    /**
     * 测试feign调用
     */
    @GetMapping("/example/hello")
    public String getMessage(@RequestParam Integer time) {
        System.out.println("进入service-provider的测试超时时间方法");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 1 / 0;
//        throw new RuntimeException();
        return "foo:" + foo;
    }

    @GetMapping("/cache")
    public String cache(Integer id) {
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        return String.valueOf(randomInt);
    }

    @GetMapping("/requestMerge/{id}")
    public String requestMergeOne(@PathVariable Integer id) {
        log.info("调用接口查询单例");
        return "merge" + id;
    }

    @GetMapping("/requestMerge")
    public List<String> requestMerge(@RequestParam("ids") List<Integer> ids) {
        log.info("调用接口查询列表");
        List<String> returnList = new ArrayList<>();
        ids.forEach(e -> {
            returnList.add("merge" + e);
        });
        return returnList;
    }
}
