/**
 * @author: linxiunan
 * @date: 2019/9/20 13:55
 * @descrption
 */
package com.linitly.service.provider.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceProviderClientFallBack implements ServiceProviderClient {

    @Override
    public String getMessage(Integer time) {
        log.error("feign调用service-provider服务getMessage接口失败");
        return "出错啦";
    }

    @Override
    public String cache(Integer id) {
        return "cache error";
    }
}
