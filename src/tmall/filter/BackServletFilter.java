package tmall.filter;

import org.apache.commons.lang.StringUtils;
import tmall.util.Page;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BackServletFilter implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
//        System.out.println("come to backservletFilter");

        String contextPath=request.getServletContext().getContextPath();
//        System.out.println(contextPath);

        String uri=request.getRequestURI();
//        System.out.println(uri);

        StringBuffer url=request.getRequestURL();
//        System.out.println(url);

        uri= StringUtils.remove(uri,contextPath);

        if(uri.startsWith("/admin_")){
            String servletPath=StringUtils.substringBetween(uri,"_","_")+"Servlet";
//            response.getWriter().println(servletPath);
            String method=StringUtils.substringAfterLast(uri,"_");

           request.setAttribute("method",method);
           servletRequest.getRequestDispatcher("/"+servletPath).forward(request,response);
           return;
        }

        filterChain.doFilter(request,response);


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
