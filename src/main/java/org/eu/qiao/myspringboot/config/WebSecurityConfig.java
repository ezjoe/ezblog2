package org.eu.qiao.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassNamg WebSecurityConfig
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 20:36
 * @Version 1.0
 **/

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    public final static String SESSION_KEY = "user";


    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        // 排除配置
        addInterceptor.excludePathPatterns("/admin/login");
        // 拦截配置
        addInterceptor.addPathPatterns("/admin");
        addInterceptor.addPathPatterns("/admin/*");
    }


    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            String path =request.getServletPath();
            if(path.contains("login")){
                return true;
            }
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                response.sendRedirect("/admin/login");
                return false;
            }

            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "拦截器中的cookie");
                if (cookie.getName().equals(SESSION_KEY)) {
                    return true;
                }
            }
            response.sendRedirect("/admin/login");
            return false;
        }
    }

}
