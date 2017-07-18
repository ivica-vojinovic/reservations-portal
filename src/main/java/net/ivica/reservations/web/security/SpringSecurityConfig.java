package net.ivica.reservations.web.security;

import net.ivica.reservations.api.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserProfileService _userProfileService;

    public SpringSecurityConfig() {
        super();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserProfileService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/index.html").failureUrl("/login-error.html").and().logout().logoutUrl("/logout.html")
                .logoutSuccessUrl("/index.html").and().authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN").and().exceptionHandling().accessDeniedPage("/403.html");

    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    private UserProfileService getUserProfileService() {
        return _userProfileService;
    }

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        _userProfileService = userProfileService;
    }

}
