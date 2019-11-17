package com.studycloud.apigateway.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置cookie
 */
public class CookieUtil {

    public static void set(HttpServletResponse response, String name, String value, Integer expire) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String token) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (token.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
