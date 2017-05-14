package net.ivica.reservations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"net.ivica.reservations.business", "net.ivica.reservations.security"})
public class SpringMainConfig {

    public SpringMainConfig() {
        super();
    }

    // Nothing else to be configured here: component scanning will do everything needed

}
