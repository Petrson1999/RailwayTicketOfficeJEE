package com.railvayticketiffice.web.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StaticResourceFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(StaticResourceFilter.class);
    private static final String RESOURCES_PATH = "/resources/";
    private static final String UI_PATH = "/built/";
    private static final String MODE_MODULES_PATH = "/node_modules/";
    private static final String APP_PATH = "/app";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LOG.info("Do service, URI: " + httpServletRequest.getRequestURI());
        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        if (shouldBeSkipped(path)) {
            LOG.info("Static resource: " + path);
            chain.doFilter(request, response);
            return;
        }

        String pathToForward = APP_PATH + path;
        LOG.info("Non static resource: " + path + ". New Path: " + pathToForward);

        httpServletRequest.getRequestDispatcher(pathToForward).forward(request, response);

    }

    private boolean shouldBeSkipped(String path) {
        return path.startsWith(RESOURCES_PATH) || path.startsWith(UI_PATH) || path.startsWith(MODE_MODULES_PATH) ||
                path.startsWith(APP_PATH);
    }

    @Override
    public void destroy() {
    }
}
