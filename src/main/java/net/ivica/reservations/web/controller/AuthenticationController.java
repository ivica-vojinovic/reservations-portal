package net.ivica.reservations.web.controller;

import net.ivica.reservations.api.LogHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

    private static final Log LOG = LogFactory.getLog(AuthenticationController.class);

    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }

    @RequestMapping("/login-error.html")
    public String loginError(HttpServletRequest request, Model model) {
        AuthenticationException exception = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (exception != null) {
            LogHelper.error(LOG, "Login error", exception);
            model.addAttribute("loginError", exception.getLocalizedMessage());
        }

        return "index";
    }

    @RequestMapping("/logout.html")
    public String logout() {
        return "index";
    }

}
