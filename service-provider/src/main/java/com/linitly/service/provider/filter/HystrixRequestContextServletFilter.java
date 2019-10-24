/**
 * @author: linxiunan
 * @date: 2019/9/26 14:07
 * @descrption
 */
package com.linitly.service.provider.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "hystrixRequestContextServletFilter", urlPatterns = "/*", asyncSupported = true)
public class HystrixRequestContextServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @author linxiunan
     * @date 2019/9/26 14:07
     * @description 初始化Hystrix上下文有两种方式
     * 1. 在每个用到请求缓存的Controller方法的开始初始化：HystrixRequestContext context = HystrixRequestContext.initializeContext();最后关闭：context.close();
     * 2. 使用filter，启动类上加上@ServletComponentScan，请求之前初始化，最后关闭上下文
     * 该功能是用于请求缓存和请求合并功能中，实际使用较少
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //初始化Hystrix请求上下文
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            //请求正常通过
            chain.doFilter(request, response);
        } finally {
            //关闭Hystrix请求上下文
            context.shutdown();
        }
    }

    @Override
    public void destroy() {

    }
}
