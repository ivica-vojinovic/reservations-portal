package net.ivica.reservations.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class ChangeLanguageController {

    @GetMapping("/changeLanguage.html")
    public String changeLanguage(HttpServletRequest request, HttpServletResponse response, String userLanguage, String page) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response, Locale.forLanguageTag(userLanguage));

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
        int year = now.getYear();
        year = year + 10;
        ZonedDateTime expiresDate = now.plusYears(10);
        String expires = DateTimeFormatter.RFC_1123_DATE_TIME.format(expiresDate);

        String cookieValue = "userLanguage=" + userLanguage + ";" + expires;
        Cookie cookie = WebUtils.getCookie(request, "userLanguage");
        if (cookie != null) {
            cookie.setValue(cookieValue);
        } else {
            cookie = new Cookie("userLanguage", cookieValue);
        }

        return "redirect:/".concat(page).concat(".html");
    }

}
