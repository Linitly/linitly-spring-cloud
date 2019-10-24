/**
 * @author: linxiunan
 * @date: 2019/10/11 16:35
 * @descrption
 */
package com.linitly.feign.client.controller.v1_0_1;

import com.linitly.service.provider.entity.common.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0.1")
public class Version3Controller extends Version2Controller {

    @GetMapping("/version11")
    public ResponseResult version1() {
        return super.version1();
    }

    @GetMapping("/version21")
    public ResponseResult version2() {
        return super.version2();
    }
}
