package com.diden.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Configuration
@WebFilter(urlPatterns = {"/user/*", "/"})
public class ResponseJsonCommonFilter implements Filter {
    final static Logger logger = LoggerFactory.getLogger(ResponseJsonCommonFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("=============================== request {} ===============================", request.getContentType());
        logger.info("=============================== response {} ===============================", response.getContentType());

        response.setContentType("application/json;charset=UTF-8");
        chain.doFilter(request, response);

        logger.info("=============================== request {} ===============================", request.getContentType());
        logger.info("=============================== response {} ===============================", response.getContentType());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
