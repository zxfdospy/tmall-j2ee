package tmall.servlet;

import org.springframework.web.util.HtmlUtils;
import tmall.bean.User;
import tmall.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    public String login(HttpServletRequest request, HttpServletResponse response, Page page) {
        String name=request.getParameter("name");
        name= HtmlUtils.htmlEscape(name);
        String password=request.getParameter("password");
        User admin=userDAO.getAdmin(name,password);
        if (null == admin) {
            request.setAttribute("msg", "账号密码错误");
            return "adminlogin.jsp";
        }
        request.getSession().setAttribute("admin", admin);
        return "@admin_category_list";
    }

    public String loginout(HttpServletRequest request, HttpServletResponse response, Page page) {
        request.getSession().removeAttribute("admin");
        return "adminlogin.jsp";
    }

}
