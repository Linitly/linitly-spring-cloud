/**
 * @author: linxiunan
 * @date: 2019/9/19 14:15
 * @descrption
 */
package com.linitly.service.provider.controller;

import com.linitly.service.provider.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {

    @Autowired
    private TestService testService;

    @GetMapping("/test-retry")
    public String testRetry() {
        int store = testService.testRetry(-1);
        return "success";
    }
}
