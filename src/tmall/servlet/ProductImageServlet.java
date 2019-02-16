package tmall.servlet;

import org.apache.commons.fileupload.FileItem;
import tmall.bean.Product;
import tmall.bean.ProductImage;
import tmall.dao.ProductImageDAO;
import tmall.util.ImageUtil;
import tmall.util.Page;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductImageServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        Map<String, String> params = new HashMap<>();
        FileItem fl = null;
        fl = parseUpload(request, params);
        //根据上传的参数生成productImage对象
        String type = params.get("type");
        int pid = Integer.parseInt(params.get("pid"));
        Product p = productDAO.get(pid);

        ProductImage pi = new ProductImage();
        pi.setType(type);
        pi.setProduct(p);
        productImageDAO.add(pi);
        //生成文件
        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageDAO.type_single.equals(pi.getType())) {
            imageFolder = request.getSession().getServletContext().getRealPath("img/productSingle");
            imageFolder_small = request.getSession().getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = request.getSession().getServletContext().getRealPath("img/productSingle_middle");
        } else
            imageFolder = request.getSession().getServletContext().getRealPath("img/productDetail");
        File file = new File(imageFolder, fileName);
        file.getParentFile().mkdirs();
        try {
            if (fl != null && fl.getSize() != 0) {
                fl.write(file);
                BufferedImage img = ImageUtil.change2jpg(file);
                ImageIO.write(img, "jpg", file);
                if (ProductImageDAO.type_single.equals(pi.getType())) {
                    File f_small = new File(imageFolder_small, fileName);
                    File f_middle = new File(imageFolder_middle, fileName);
                    f_small.getParentFile().mkdirs();
                    f_middle.getParentFile().mkdirs();
                    ImageUtil.resizeImage(file, 56, 56, f_small);
                    ImageUtil.resizeImage(file, 217, 190, f_middle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "@admin_productImage_list?pid=" + p.getId();
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductImage pi = productImageDAO.get(id);
        productImageDAO.delete(id);

        if (ProductImageDAO.type_single.equals(pi.getType())) {
            String imageFolder_single = request.getSession().getServletContext().getRealPath("img/productSingle");
            String imageFolder_small = request.getSession().getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle = request.getSession().getServletContext().getRealPath("img/productSingle_middle");

            File f_single = new File(imageFolder_single, pi.getId() + ".jpg");
            f_single.delete();
            File f_small = new File(imageFolder_small, pi.getId() + ".jpg");
            f_small.delete();
            File f_middle = new File(imageFolder_middle, pi.getId() + ".jpg");
            f_middle.delete();

        } else {
            String imageFolder_detail = request.getSession().getServletContext().getRealPath("img/productDetail");
            File f_detail = new File(imageFolder_detail, pi.getId() + ".jpg");
            f_detail.delete();
        }
        return "@admin_productImage_list?pid=" + pi.getProduct().getId();
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
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product p = productDAO.get(pid);
        List<ProductImage> pisSingle = productImageDAO.list(p, ProductImageDAO.type_single);
        List<ProductImage> pisDetail = productImageDAO.list(p, ProductImageDAO.type_detail);

        request.setAttribute("p", p);
        request.setAttribute("pisSingle", pisSingle);
        request.setAttribute("pisDetail", pisDetail);

        return "admin/listProductImage.jsp";
    }
}
