package tmall.util;
 
import tmall.bean.Product;
import tmall.bean.User;
import tmall.dao.ProductDAO;
import tmall.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "tmall";
//    static String encoding = "UTF-8";
    static String loginName = "TmallDBUser";
    static String password = "Tmall_j2ee";
 
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true", ip, port, database);
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
//         User u=new UserDAO().getAdmin("admin","12345");
//        System.out.println(u.getName());
    }
 
}