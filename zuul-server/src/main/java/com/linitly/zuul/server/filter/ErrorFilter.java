/**
 * @author: linxiunan
 * @date: 2019/10/12 11:01
 * @descrption
 */
package com.linitly.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // zuul的过滤器中不适用spring的全局异常处理，网关过滤器发生异常时，到error过滤器处理
        // 此处只打印记录错误信息，不直接返回信息给前端(考虑后续异常处理可以调整，将处理和返回分开，方便扩展)，也可在此处直接返回给前端(PrintWriter)
        RequestContext requestContext = RequestContext.getCurrentContext();
        Throwable throwable = requestContext.getThrowable();
        log.info("Filter Error : {}", throwable.getCause().getMessage());
        return null;
    }
}
