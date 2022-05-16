package com.diden.servlet;

import com.diden.filter.ResponseJsonCommonFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@WebServlet(urlPatterns = {"/user/*", "/"})
public class ResponseJsonCommonServlet extends HttpServlet {
    final static Logger logger = LoggerFactory.getLogger(ResponseJsonCommonServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        logger.info("doGet == {}", resp.getContentType());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        logger.info("service == {}", resp.getContentType());

    }
}
