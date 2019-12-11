package com.railvayticketiffice.web.filters;

import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.enums.Role;
import com.railvayticketiffice.security.SecurityConfig;
import com.railvayticketiffice.services.PageService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AuthenticationFilter.class);
    private PageService pageService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pageService = new PageService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String path = pageService.getPath(httpServletRequest);

        if (isNotSecuredPage(path)) {
            LOG.debug("Page is not secured: " + path);
            chain.doFilter(request, response);
            return;
        }
        String contextPath = httpServletRequest.getContextPath();

        HttpSession session = httpServletRequest.getSession();
        
        User user = (User) session.getAttribute("user");
        if (user == null) {
            LOG.info("User not logged");
            httpServletResponse.sendRedirect(contextPath + "/");
            return;
        }

        if (hasNotPermission(path, user.getRole())) {
            LOG.debug("User has not permission : " + user + " , " + path);
            httpServletResponse.sendRedirect(contextPath + "/403");
            return;
        }

        LOG.debug("User has permission. Continue");
        chain.doFilter(request, response);
    }

    private boolean hasNotPermission(String path, Role role) {
        return !SecurityConfig.hasPermission(path, role);
    }

    private boolean isNotSecuredPage(String path) {
        return !SecurityConfig.isSecurePage(path);
    }

    @Override
    public void destroy() {

    }
}
