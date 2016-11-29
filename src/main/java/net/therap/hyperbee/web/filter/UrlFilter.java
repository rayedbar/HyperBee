package net.therap.hyperbee.web.filter;

import net.therap.hyperbee.web.security.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static net.therap.hyperbee.utils.constant.Url.LOGIN_URL;

/**
 * @author rayed
 * @since 11/24/16 1:29 PM
 */
public class UrlFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("", "/login", "/logout", "/signUp")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        if (ALLOWED_PATHS.contains(requestURI)){
            System.out.println("true");
            chain.doFilter(request, response);

            return;
        }

        HttpSession session = ((HttpServletRequest) request).getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Cache-Control", "no-cach, no-store, must-revalidate");
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setHeader("Expires", "0");

        if (authUser != null){
            chain.doFilter(request, response);

            return;
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(LOGIN_URL);
    }

    @Override
    public void destroy() {

    }
}
