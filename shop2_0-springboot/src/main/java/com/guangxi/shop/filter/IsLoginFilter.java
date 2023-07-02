package com.guangxi.shop.filter;


import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "IsLoginFilter",urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class IsLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将参数类型转换为HttpServlet....
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求中的请求路径
        String servletPath = request.getServletPath();
        //在所有操作之前要进行判断是否是 不需要拦截的请求，例如（首页，登录,注册）
        if("/userController/login-servlet".equals(servletPath)||"/login.jsp".equals(servletPath)||
           "/productController/home-servlet".equals(servletPath)||"/home.jsp".equals(servletPath)||
           "/userController/register-servlet".equals(servletPath)||"/register.jsp".equals(servletPath)||
           "/productController/show-servlet".equals(servletPath)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }else{
            //获取cookie中的token值（user）
            Cookie[] cookies = request.getCookies();
            if(cookies!=null&&cookies.length>0){
                for (Cookie cookie : cookies) {     //对cookie进行一个遍历
                    if(cookie.getName().equals("user")){    //如果有token
                        String token = cookie.getValue();
                        Jedis jedis = new Jedis();     //到redis中，拿着token为键去找是否有值或者是是否有这个key
                        String userJsonInfo = jedis.get(token);   //如果没有这个key的话，返回的会是null值
                        if(userJsonInfo!=null&&!"".equals(userJsonInfo)){   //满足时 这时说明redis中有这个用户信息，用户已登录
                            filterChain.doFilter(servletRequest,servletResponse);     //放行
                            return;
                        }else{    //不满足 （redis信息过期了，需要重新登录）
                            //跳转到登录页面
                            response.sendRedirect("/login.jsp");
                            return;
                        }
                    }
                }
                response.sendRedirect("/login.jsp");
                return;
            }else{
                response.sendRedirect("/login.jsp");
                return;
            }
        }
    }

    @Override
    public void destroy() {

    }
}
