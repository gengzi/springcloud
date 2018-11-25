package club.gsjblog.springcloud.client.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 *  访问过滤器
 *  zuul 提供两大核心功能
 *  路由转发
 *  请求过滤
 *
 */
public class AccessFilter  extends ZuulFilter{

    private static Logger log  = LoggerFactory.getLogger(AccessFilter.class);


    @Override
    public String filterType() {
        // 过滤器的类型，它决定过滤器在请求的那个生命周期中执行， pre 在请求
        // 被路由前执行
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤器的执行顺序，当请求在一个阶段中存在多个过滤器时，
        //需要根据该方法返回的值顺序来执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //判断该过滤器是否需要被执行
        return true;
    }

    /**
     * 实现对请求判断，判断是否携带 accessToken
     * 如果携带，zuul 路由转发    请求： http://localhost:8000/api-a/hello?accessToken=111
     * 如果没有，zuul不做路由转发，响应用户失败   请求：http://localhost:8000/api-a/hello
     *
     * @return
     */
    @Override
    public Object run() {
        //过滤器的具体逻辑
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        log.info("request info:"+request.getMethod()+","+request.getRequestURL().toString());

        String accessToken = request.getParameter("accessToken");
        if (accessToken == null){
            log.warn("access token is empty ");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("no token，request failed");
            return null;
        }
        log.info("access Token ok");
        return null;
    }
}
