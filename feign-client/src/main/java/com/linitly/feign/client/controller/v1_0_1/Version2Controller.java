/**
 * @author: linxiunan
 * @date: 2019/10/11 16:35
 * @descrption
 */
package com.linitly.feign.client.controller.v1_0_1;

import com.linitly.feign.client.controller.v1_0_0.Version1Controller;
import com.linitly.service.provider.entity.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0.1")
public class Version2Controller extends Version1Controller {

    @Autowired
    private Version1Controller version1Controller;

    @GetMapping("/version1")
    public ResponseResult version1() {
        return super.version1();
    }

    @GetMapping("/version2")
    public ResponseResult version2() {
        return new ResponseResult(200, "version2 from 2");
    }
}
