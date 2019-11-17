//package com.studycloud.apigateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.studycloud.apigateway.constant.RedisConstant;
//import com.studycloud.apigateway.util.CookieUtil;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
//
///**
// * 权限拦截  区分买家和卖家
// *
// *
// *
// * 不好维护
// * 分解成两个filter
// */
//@Component
//public class AuthFilter extends ZuulFilter {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public String filterType() {
//        return PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return PRE_DECORATION_FILTER_ORDER-1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        /**
//         *  /order/create 只能买家访问（cookie里有 openid）
//         *  /order/finish 只能卖家访问（cookie里有 token,redis有值）
//         *  /product/list 均可访问
//         */
//        if("/order/order/create".equals(request.getRequestURI())){
//            Cookie cookie = CookieUtil.get(request, "openid");
//            if(cookie == null || StringUtils.isEmpty(cookie.getName())){
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            }
//        }
//        if("/order/order/finish".equals(request.getRequestURI())){
//            Cookie cookie = CookieUtil.get(request, "token");
//            if(cookie == null
//                    || StringUtils.isEmpty(cookie.getName())
//                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            }
//        }
//        return null;
//    }
//}
