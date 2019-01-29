package tmall.servlet;

import tmall.bean.Category;
import tmall.bean.Property;
import tmall.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PropertyServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        String name=request.getParameter("name");
        int cid=Integer.parseInt(request.getParameter("cid"));
        Category c=categoryDAO.get(cid);
        Property p=new Property();
        p.setCategory(c);
        p.setName(name);
        propertyDAO.add(p);
        return "@admin_property_list?cid="+cid;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id=Integer.parseInt(request.getParameter("id"));
        Property p=propertyDAO.get(id);
        propertyDAO.delete(id);
        return "@admin_property_list?cid="+p.getCategory().getId();
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id=Integer.parseInt(request.getParameter("id"));
        Property p=propertyDAO.get(id);
//        int cid=p.getCategory().getId();
        request.setAttribute("property",p);
//        request.setAttribute("cid",cid);
        return "admin/editProperty.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id=Integer.parseInt(request.getParameter("propertyEditId"));
        String name=request.getParameter("propertyEditName");
        Property p=propertyDAO.get(id);
        p.setName(name);
        propertyDAO.update(p);
        return "@admin_property_list?cid="+p.getCategory().getId();
    }


//    @Override
//    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
//        int id=Integer.parseInt(request.getParameter("id"));
//        String name=request.getParameter("name");
//        Property p=propertyDAO.get(id);
//        p.setName(name);
//        propertyDAO.update(p);
//        return "@admin_property_list?cid="+p.getCategory().getId();
//    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid=Integer.parseInt(request.getParameter("cid"));
        Category c=categoryDAO.get(cid);
        List<Property> ps=propertyDAO.list(cid,page.getStart(),page.getCount());
        int total=propertyDAO.getTotal(cid);
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        request.setAttribute("ps",ps);
        request.setAttribute("page",page);
        request.setAttribute("c",c);

        return "admin/listProperty.jsp";
    }
}
