package tmall.servlet;

import org.apache.commons.lang.StringUtils;
import tmall.bean.Category;
import tmall.bean.Product;
import tmall.bean.PropertyValue;
import tmall.util.DateUtil;
import tmall.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class ProductServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);

        String name = request.getParameter("name");
        String subTitle = request.getParameter("subTitle");
        double dOriginalPrice = Double.parseDouble(request.getParameter("originalPrice"));
        dOriginalPrice *= 100;
        String sOriginalPrice = String.valueOf(dOriginalPrice);
        sOriginalPrice = StringUtils.substringBeforeLast(sOriginalPrice, ".");
        long originalPrice = Long.parseLong(sOriginalPrice);
        double dPromotePrice = Double.parseDouble(request.getParameter("promotePrice"));
        dPromotePrice *= 100;
        String sPromotePrice = String.valueOf(dPromotePrice);
        sPromotePrice = StringUtils.substringBeforeLast(sPromotePrice, ".");
        long promotePrice = Long.parseLong(sPromotePrice);
        int stock = Integer.parseInt(request.getParameter("stock"));


        Product p = new Product();

        p.setCategory(c);
        p.setName(name);
        p.setSubTitle(subTitle);
        p.setOriginalPrice(originalPrice);
        p.setPromotePrice(promotePrice);
        p.setStock(stock);
        p.setCreateDate(new Date());
        productDAO.add(p);


        return "@admin_product_list?cid=" + cid;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        int pageStatus = Integer.parseInt(request.getParameter("page.start"));
        Product p = productDAO.get(id);
        productDAO.delete(id);
        int total = productDAO.getTotal(p.getCategory().getId());
        if (pageStatus != 0) {
            if (total % page.getCount() == 0)
                pageStatus = pageStatus - page.getCount();
        }
        return "@admin_product_list?page.start=" + pageStatus + "&cid=" + p.getCategory().getId();
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    public String editPropertyValue(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product p = productDAO.get(id);
        request.setAttribute("p", p);

        propertyValueDAO.init(p);

        List<PropertyValue> pvs = propertyValueDAO.list(p.getId());

        request.setAttribute("pvs", pvs);

        return "admin/editProductValue.jsp";
    }

    public String updatePropertyValue(HttpServletRequest request, HttpServletResponse response, Page page) {
        int pvid = Integer.parseInt(request.getParameter("pvid"));
        String value = request.getParameter("value");

        PropertyValue pv = propertyValueDAO.get(pvid);
        pv.setValue(value);
        propertyValueDAO.update(pv);
        return "%success";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        String pageStatus = request.getParameter("productEditPageStatus");
        int pid = Integer.parseInt(request.getParameter("productEditId"));
        Product p = productDAO.get(pid);
        p.setName(request.getParameter("productEditName"));
        p.setSubTitle(request.getParameter("productEditSubTitle"));
        double dOriginalPrice = Double.parseDouble(request.getParameter("productEditOriginalPrice"));
        dOriginalPrice *= 100;
        String sOriginalPrice = String.valueOf(dOriginalPrice);
        sOriginalPrice = StringUtils.substringBeforeLast(sOriginalPrice, ".");
        long originalPrice = Long.parseLong(sOriginalPrice);
        p.setOriginalPrice(originalPrice);
        double dPromotePrice = Double.parseDouble(request.getParameter("productEditPromotePrice"));
        dPromotePrice *= 100;
        String sPromotePrice = String.valueOf(dPromotePrice);
        sPromotePrice = StringUtils.substringBeforeLast(sPromotePrice, ".");
        long promotePrice = Long.parseLong(sPromotePrice);
        p.setPromotePrice(promotePrice);
        int stock = Integer.parseInt(request.getParameter("productEditStock"));
        p.setStock(stock);

        productDAO.update(p);
        return "@admin_product_list?page.start=" + pageStatus + "&cid=" + p.getCategory().getId();
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);
        List<Product> ps = productDAO.list(cid, page.getStart(), page.getCount());
        int total = productDAO.getTotal(cid);
        page.setTotal(total);
        page.setParam("&cid=" + cid);

        request.setAttribute("ps", ps);
        request.setAttribute("c", c);
        request.setAttribute("page", page);

        return "admin/listProduct.jsp";
    }
}
