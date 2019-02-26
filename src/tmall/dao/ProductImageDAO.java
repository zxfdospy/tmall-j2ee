package tmall.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import tmall.bean.Product;
import tmall.bean.ProductImage;
import tmall.util.DBUtil;
  
public class ProductImageDAO {
  
    public static final String type_single = "type_single";
    public static final String type_detail = "type_detail";
 
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from ProductImage";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
  
    public void add(ProductImage bean) {
 
        String sql = "insert into ProductImage values(null,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, bean.getProduct().getId());
            ps.setString(2, bean.getType());
            ps.setInt(3,bean.getLocation());
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }

    public void update(ProductImage bean) {
        String sql = "update productImage set pid=?,type=?,location=? where id = ?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,bean.getProduct().getId());
            ps.setString(2,bean.getType());
            ps.setInt(3,bean.getLocation());
            ps.setInt(4,bean.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from ProductImage where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public ProductImage get(int id) {
        ProductImage bean = null;
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from ProductImage where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean=new ProductImage();
                int pid = rs.getInt("pid");
                String type = rs.getString("type");
                int location=rs.getInt("location");
                Product product = new ProductDAO().get(pid);
                bean.setProduct(product);
                bean.setType(type);
                bean.setLocation(location);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<ProductImage> list(Product p, String type) {
        return list(p, type,0, Short.MAX_VALUE);
    }
  
    public List<ProductImage> list(Product p, String type, int start, int count) {
        List<ProductImage> beans = new ArrayList<ProductImage>();
  
        String sql = "select * from ProductImage where pid =? and type =? order by location limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, p.getId());
            ps.setString(2, type);
  
            ps.setInt(3, start);
            ps.setInt(4, count);
             
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
 
                ProductImage bean = new ProductImage();
                int id = rs.getInt(1);
                int location=rs.getInt("location");
 
                bean.setProduct(p);
                bean.setType(type);
                bean.setId(id);
                bean.setLocation(location);
                   
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }

    public static void main(String[] args) {
//        List<Product> ps=new ProductDAO().list();
//        for(Product p:ps){
//            List<ProductImage> pis=new ProductImageDAO().list(p,type_single);
//            int index=0;
//            for(ProductImage pi:pis){
//                pi.setLocation(index);
//                new ProductImageDAO().update(pi);
//                index++;
//            }
//            List<ProductImage> pis2=new ProductImageDAO().list(p,type_detail);
//            index=0;
//            for(ProductImage pi:pis2){
//                pi.setLocation(index);
//                new ProductImageDAO().update(pi);
//                index++;
//            }
//        }

    }
  
}