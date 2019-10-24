/**
 * @author: linxiunan
 * @date: 2019/8/23 10:24
 * @descrption
 */
package com.linitly.service.provider.service;

import com.linitly.service.provider.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Slf4j
@Service
public class TestService {

    private final int totalNum = 100000;

    /**
     * @author linxiunan
     * @date 2019/8/23 10:43
     * @return int
     * @description 使用时在启动类上加上@EnableRetry注解
     * value：抛出指定异常才会重试
     * include：和value一样，默认为空，当exclude也为空时，默认所以异常
     * exclude：指定不处理的异常
     * maxAttempts：最大重试次数，默认3次
     * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L
     * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒
     */
    @Retryable(value = CommonException.class)
    public int testRetry(int num) {
        log.info("减库存开始" + LocalTime.now());
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("illegal");
        }
        if (num <= 0) {
            throw new IllegalArgumentException("数量不对");
        }
        log.info("减库存执行结束" + LocalTime.now());
        return totalNum - num;
    }
}
