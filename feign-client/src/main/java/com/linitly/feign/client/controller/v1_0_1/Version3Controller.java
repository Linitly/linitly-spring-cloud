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

    /**
     * 使用此种方式可以较好的控制版本控制，且代码不用复用，需要修改的地方可以直接修改，不修改的部分直接使用原来的实现
     * service同样使用这种继承类的方式实现版本控制，而dao需要各自实现，可使用方法名+版本号的方式，sql语句追加而不修改
     * 此类方式需要所有父类的方法都实现，不可以出现中间断层，否则会报错
     * 未试验：通过父类参数校验可不可行
     */
    @GetMapping("/version11")
    public ResponseResult version1() {
        return super.version1();
    }

    @GetMapping("/version21")
    public ResponseResult version2() {
        return super.version2();
    }
}
