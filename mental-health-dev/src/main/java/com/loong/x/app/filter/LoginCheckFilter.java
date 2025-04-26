package com.loong.x.app.filter;

import com.alibaba.fastjson.JSON;
import com.loong.x.common.utils.BaseContextUtils;
import com.loong.x.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description
 * 检查用户是否已经完成登录
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2023/8/4 10:22
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("==========================================================================");
        System.out.println("==========================================================================");
        System.out.println("==========================================================================");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;



        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();// /backend/index.html

        log.info("拦截到请求：{}", requestURI);

        //定义不需要处理的请求路径--放行路径
        String[] urls = new String[]{
                "/user/login",
                "/user/info",
                "/user/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3、如果不需要处理，则直接放行
        if (check) {
            log.info("本次请求{}不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9099");
        Object user1 = request.getSession().getAttribute("user");
        System.out.println("这个结果：" + user1);
        //4、判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("user") != null) {
            log.info("用户已登录，用户id为：{}", request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContextUtils.setCurrentId(userId);


            System.out.println("我看看数据：" + BaseContextUtils.getCurrentId());

            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls       路径
     * @param requestURI 请求路径
     * @return 匹配结果
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
