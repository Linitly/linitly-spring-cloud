/**
 * @author: linxiunan
 * @date: 2019/10/23 13:50
 * @descrption
 */
package com.linitly.feign.client.controller.admin;

import com.linitly.service.provider.entity.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后端测试接口")
@RestController
@RequestMapping("/v${info.version}")
public class AdminTest1Controller {

    @GetMapping("/admin/test")
    @ApiOperation("后端测试接口")
    public ResponseResult adminTest() {
        return new ResponseResult();
    }
}
