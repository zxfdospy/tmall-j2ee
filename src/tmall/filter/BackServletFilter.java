package tmall.filter;

import org.apache.commons.lang3.StringUtils;
import tmall.bean.User;
import tmall.util.*;

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contextPath = request.getServletContext().getContextPath();
        ///tmall
//        System.out.println("contextPath:"+contextPath);

        String uri = request.getRequestURI();
//        System.out.println("addr="+request.getLocalName());
        ///tmall/admin_category_list
//        System.out.println("uri:"+uri);

//        StringBuffer url=request.getRequestURL();
//        System.out.println("url"+url);

//        String location=StringUtils.remove(url.toString(),uri);
//        String location=url.substring(7,url.length()-uri.length()+1);
//        System.out.println(location);
        uri = StringUtils.remove(uri, contextPath);

        if (uri.startsWith("/admin_")) {
            String servletPath = StringUtils.substringBetween(uri, "_", "_") + "Servlet";
//            response.getWriter().println(servletPath);
            String method = StringUtils.substringAfterLast(uri, "_");
            if (!method.equals("login")) {

//                redis session
                String admintoken = CookieUtil.readLoginToken(request, CookieUtil.COOKIE_NAME_ADMIN);
                if (StringUtils.isNotEmpty(admintoken)) {
                    String adminstr = RedisPoolUtil.get(admintoken);
                    User admin = JsonUtil.string2Obj(adminstr, User.class);
                    if (admin == null) {
                        response.sendRedirect("adminlogin.jsp");
                        return;
                    }else {
                        admin.setPassword("");
                        request.setAttribute("admin",admin);
                        RedisPoolUtil.expire(admintoken, 60*30);//30分钟
                    }
                } else {
                    response.sendRedirect("adminlogin.jsp");
                    return;
                }


//                自带session
//                User admin = (User) request.getSession().getAttribute("admin");
//                if (null == admin) {
//                    response.sendRedirect("adminlogin.jsp");
//                    return;
//                }
            }

            request.setAttribute("method", method);
            servletRequest.getRequestDispatcher("/" + servletPath).forward(request, response);
            return;
        }

        filterChain.doFilter(request, response);


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
