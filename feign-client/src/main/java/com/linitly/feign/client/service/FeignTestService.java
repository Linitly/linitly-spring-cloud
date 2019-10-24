/**
 * @author: linxiunan
 * @date: 2019/9/26 13:53
 * @descrption
 */
package com.linitly.feign.client.service;

import com.linitly.service.provider.clients.ServiceProviderClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Service
public class FeignTestService {

    @Autowired
    private ServiceProviderClient serviceProviderClient;
    @Autowired
    private RestTemplate restTemplate;

    @CacheResult
    @HystrixCommand(commandKey = "cacheKey", fallbackMethod = "cacheError")
    public String cache(Integer id) {
        return serviceProviderClient.cache(id);
    }

    @CacheRemove(commandKey = "cacheKey")
    @HystrixCommand
    public void cacheRemove(Integer id) {
        log.info("缓存清除");
    }

    /**
     * @author linxiunan
     * @date 2019/9/26 16:30
     * @return java.lang.String
     * @description 熔断方法，需要跟方法的参数接收一致
     */
    public String cacheError(Integer id) {
        return "cacheError";
    }

    /**
     * @author linxiunan
     * @date 2019/10/8 11:56
     * @description timerDelayInMilliseconds为合并等待时间，默认为100ms
     */
    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "200")
    })
    public Future<String> findOne(Integer id) {
        return null;
    }

    @HystrixCommand
    public List<String> findAll(List<Integer> ids) {
        log.info("请求的合并" + ids.toString());
        String[] resultStr = restTemplate.getForObject("http://SERVICE-PROVIDER/requestMerge?ids={1}", String[].class, StringUtils.join(ids, ","));
        return Arrays.asList(resultStr);
    }
}
