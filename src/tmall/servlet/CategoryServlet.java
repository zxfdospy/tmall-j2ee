package tmall.servlet;

import org.apache.commons.fileupload.FileItem;
import tmall.bean.Category;
import tmall.util.ImageUtil;
import tmall.util.Page;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryServlet extends BaseBackServlet{
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        Map<String,String> params=new HashMap<>();
        FileItem fi=super.parseUpload(request,params);
        String name=params.get("name");
        Category c=new Category();
        c.setName(name);
        categoryDAO.add(c);
        //使用fileitem上传
        File imageFolder=new File(request.getSession().getServletContext().getRealPath("img/category"));
//        System.out.println(request.getSession().getServletContext().getRealPath(""));
//        System.out.println(imageFolder.getAbsoluteFile());
        File file=new File(imageFolder,c.getId()+".jpg");
        file.getParentFile().mkdirs();
        try{
            if(fi!=null&&fi.getSize()!=0){
                fi.write(file);
                BufferedImage img= ImageUtil.change2jpg(file);
                ImageIO.write(img,"jpg",file);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "@admin_category_list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id=Integer.parseInt(request.getParameter("id"));
        categoryDAO.delete(id);
        //同时删除category对应文件
        File path=new File(request.getSession().getServletContext().getRealPath("img/category"));
        File img=new File(path,id+".jpg");
        if(img.exists())
            img.delete();
        return "@admin_category_list";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Category c = categoryDAO.get(id);
        request.setAttribute("c", c);
        return "admin/editCategory.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        Map<String,String> params=new HashMap<>();
        FileItem is=super.parseUpload(request,params);
//        long s=is.getSize();
        int id=Integer.parseInt(params.get("id"));
        Category c=categoryDAO.get(id);
        c.setName(params.get("name"));
        categoryDAO.update(c);

        //使用fileitem上传
        File imageFolder=new File(request.getSession().getServletContext().getRealPath("img/category"));
//        System.out.println(request.getSession().getServletContext().getRealPath(""));
//        System.out.println(imageFolder.getAbsoluteFile());
        File file=new File(imageFolder,c.getId()+".jpg");
        file.getParentFile().mkdirs();
        try{
            if(is.getSize()!=0){
                is.write(file);
                BufferedImage img= ImageUtil.change2jpg(file);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "@admin_category_list";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        List<Category> cs=categoryDAO.list(page.getStart(),page.getCount());
        int total=categoryDAO.getTotal();
        page.setTotal(total);
        request.setAttribute("thecs",cs);
        request.setAttribute("page",page);
        return "admin/listCategory.jsp";
    }
}
