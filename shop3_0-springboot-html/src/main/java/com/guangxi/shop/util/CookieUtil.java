package com.guangxi.shop.util;

import reactor.util.annotation.Nullable;

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie setCookie(String key ,Object value){
        Cookie cookie = new Cookie(key, (String) value);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        return cookie;
    }
}
