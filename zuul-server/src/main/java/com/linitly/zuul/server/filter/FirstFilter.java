/**
 * @author: linxiunan
 * @date: 2019/10/12 10:34
 * @descrption
 */
package com.linitly.zuul.server.filter;

import com.linitly.service.provider.entity.common.ResponseResult;
import com.linitly.service.provider.enums.GlobalEnum;
import com.linitly.service.provider.exception.CommonException;
import com.linitly.service.provider.helper.constant.AdminGlobalConstant;
import com.linitly.service.provider.helper.constant.GlobalConstant;
import com.linitly.service.provider.util.JsonUtils;
import com.linitly.zuul.server.constant.ZuulConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class FirstFilter extends ZuulFilter {

    /**
     * pre: 可以在请求被路由之前调用。适用于身份认证的场景，认证通过后再继续执行下面的流程。
     * route: 在路由请求时被调用。适用于灰度发布场景，在将要路由的时候可以做一些自定义的逻辑。
     * post: 在 route 和 error 过滤器之后被调用。这种过滤器将请求路由到达具体的服务之后执行。适用于需要添加响应头，记录响应日志等应用场景。
     * error: 处理请求时发生错误时被调用。在执行过程中发送错误时会进入 error 过滤器，可以用来统一记录错误信息。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /** 过滤器的执行顺序，数值越小，优先级越高。 */
    @Override
    public int filterOrder() {
        return 0;
    }

    /** 是否执行该过滤器，true 为执行，false 为不执行，这个也可以利用配置中心来实现，达到动态的开启和关闭过滤器。
     *  通过RequestContext获取前面传递的值，判断是否执行此过滤器
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Object stillGoOn = requestContext.get(ZuulConstant.ZUUL_FILTER_GO_ON);
        return stillGoOn == null ? true : Boolean.parseBoolean(stillGoOn.toString());
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader(AdminGlobalConstant.ADMIN_TOKEN);
        if (StringUtils.isBlank(token)) {
//            throw new CommonException(GlobalEnum.DUPLICATE_KEY_ERROR);
            int i = 2 / 0;
            // 停止转发到服务上去
            requestContext.setSendZuulResponse(false);
            // zuul过滤器中传递值，此处传递值也可以用作判断过滤器是否执行
            requestContext.set(ZuulConstant.ZUUL_FILTER_GO_ON, true);
            // 设置response，设置返回头和返回体
            HttpServletResponse response = requestContext.getResponse();
            response.setHeader(GlobalConstant.CONTENT_TYPE, GlobalConstant.CONTENT_TYPE_VALUE);
            ResponseResult responseResult = new ResponseResult(GlobalEnum.UNAUTHORIZED);
            requestContext.setResponseBody(JsonUtils.objectToJson(responseResult, false));
        }
        return null;
    }
}
