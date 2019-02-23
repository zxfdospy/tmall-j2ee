package tmall.util;
 
import tmall.bean.Product;
import tmall.dao.ProductDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "tmall";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "admin";
 
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }
     
    public static void main(String[] args) throws SQLException {
//        List<Product> ps=new ProductDAO().list();
//        for(Product p:ps){
//            long original=p.getOriginalPrice()*100;
//            long promote=p.getPromotePrice()*100;
//            p.setOriginalPrice(original);
//            p.setPromotePrice(promote);
//            new ProductDAO().update(p);
//        }
         
    }
 
}