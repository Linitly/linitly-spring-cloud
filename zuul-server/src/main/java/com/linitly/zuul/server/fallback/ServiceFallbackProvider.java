/**
 * @author: linxiunan
 * @date: 2019/10/12 14:58
 * @descrption
 */
package com.linitly.zuul.server.fallback;

import com.linitly.service.provider.entity.common.ResponseResult;
import com.linitly.service.provider.enums.GlobalEnum;
import com.linitly.service.provider.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
@Component
public class ServiceFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        // *表示对所有服务进行回退操作，如果只想对某个服务进行回退，那么就返回需要回退的服务名称，这个名称一定要是注册到 Eureka 中的名称
        // 如果没有容错处理，仍会走errorFilter，有了容错处理就不会走errorFilter
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                if (cause != null) {
                    log.error("", cause.getCause());
                }
                ResponseResult responseResult = new ResponseResult(GlobalEnum.SYSTEM_ERROR);
                return new ByteArrayInputStream(JsonUtils.objectToJson(responseResult, false).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
