package net.ivica.reservations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = {"net.ivica.reservations.business", "net.ivica.reservations.security"})
public class SpringMainConfig {

    public SpringMainConfig() {
        super();
    }

}
