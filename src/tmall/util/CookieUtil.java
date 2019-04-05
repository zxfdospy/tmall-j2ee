package tmall.util;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public final static String COOKIE_DOMAIN = "zxfdospy.com";
    public final static String COOKIE_NAME_USER = "tmall_user_token";
    public final static String COOKIE_NAME_ADMIN = "tmall_admin_token";

    public static void writeLoginToken(HttpServletResponse response, String role, String token) {
        Cookie ck = null;
        if (StringUtils.equals(role, COOKIE_NAME_USER)) {
            ck = new Cookie(COOKIE_NAME_USER, token);
        }
        if (StringUtils.equals(role, COOKIE_NAME_ADMIN)) {
            ck = new Cookie(COOKIE_NAME_ADMIN, token);
        }
        ck.setDomain(COOKIE_DOMAIN);
        ck.setPath("/");

        //单位是秒，如果不设置maxage，cookie不会写入硬盘，只会写在内存
        ck.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(ck);
    }


    public static String readLoginToken(HttpServletRequest request, String role) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (StringUtils.equals(role, COOKIE_NAME_USER)) {
                    if (StringUtils.equals(ck.getName(), COOKIE_NAME_USER)) {
                        return ck.getValue();
                    }
                }
                if (StringUtils.equals(role, COOKIE_NAME_ADMIN)) {
                    if (StringUtils.equals(ck.getName(), COOKIE_NAME_ADMIN)) {
                        return ck.getValue();
                    }
                }
            }
        }
        return null;
    }

    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response,String role) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (StringUtils.equals(ck.getName(), role)) {
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }

}
