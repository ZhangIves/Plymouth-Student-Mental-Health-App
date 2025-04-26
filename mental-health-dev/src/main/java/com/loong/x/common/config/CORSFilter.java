package com.loong.x.common.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Profile("dev")
//public class CORSFilter implements Filter {
//
//	@SuppressWarnings("unused")
//	private FilterConfig config;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
//		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH");
//		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
//		httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
//		if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
//           httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            chain.doFilter(request, response);
//        }
//	}
//
//	@Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        config = filterConfig;
//    }
//}


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description
 * 处理跨域、放行
 * 跨域三种情况
 * 在请求时，如果出现了以下情况中的任意一种，那么它就是跨域请求：
 * 协议不同，如 http 和 https；
 * 域名不同；
 * 端口不同。
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2023/8/9 11:59
 */
@Configuration
public class CORSFilter implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .maxAge(3600)//最大保存cookie保存时长
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

}
