package com.shop.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{
     /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";
   
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }
   
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
        // 排除配置
        addInterceptor.excludePathPatterns("user/error","user/reg","user/agreeReg","user/finishReg","user/login","error");
        
        //addInterceptor.excludePathPatterns("user/reg");
       // addInterceptor.excludePathPatterns("user/agreeReg");
        //addInterceptor.excludePathPatterns("user/finishReg");
        //addInterceptor.excludePathPatterns("**");
        
    }
    
    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null) {
            	
            	return true;}
            
            String url=request.getRequestURI();
            //log.info(">>>MyInterceptor1>>>>>>>preHandle在请求处理之前进行调用（Controller方法调用之前）");
            if(url.endsWith("login"))
            	return true;
            if(url.endsWith("error"))
            	return true;
            if(url.endsWith("loginPost"))
            	return true;
            if(url.endsWith("eg"))
            	return true;
            if(url.endsWith(".jpg"))
            	return true;
            if(url.endsWith("welcome"))
            	return true;
           
            
            // 跳转登录
           
            if (session.getAttribute(SESSION_KEY) == null)
            	request.setAttribute("error", "未登录，请先登陆");
            else
            	request.setAttribute("error", "请输入正确网址");
            String Rurl = "/welcome";
           
            response.sendRedirect(Rurl);
           //response.
            
            
            return false;
        }
    }
}
