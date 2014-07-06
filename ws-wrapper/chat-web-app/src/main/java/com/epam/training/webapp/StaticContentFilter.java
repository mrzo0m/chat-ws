package com.epam.training.webapp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
* Created by Oleg_Burshinov on 24.02.14.
*/
@WebFilter("/static/*")
public class StaticContentFilter implements Filter {

    private RequestDispatcher defaultRequestDispatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultRequestDispatcher =
                filterConfig.getServletContext().getNamedDispatcher("default");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        defaultRequestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
