/**
 * @author: linxiunan
 * @date: 2019/10/11 16:35
 * @descrption
 */
package com.linitly.feign.client.controller.v1_0_0;

import com.linitly.service.provider.entity.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "版本1接口")
@RestController
@RequestMapping("/v1.0.0")
public class Version1Controller {

    @ApiOperation("版本1")
    @GetMapping("/version1")
    public ResponseResult version1() {
        return new ResponseResult(200, "version1");
    }

    @GetMapping("/version2")
    public ResponseResult version2() {
        return new ResponseResult(200, "version2 from 1");
    }
}
