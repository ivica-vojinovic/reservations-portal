package net.ivica.reservations.dao;

import net.ivica.reservations.api.Product;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class SpringDaoConfig {

    @Bean
    public DataSource dataSource() {
        MariaDbDataSource dataSource = null;
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/portal?useUnicode=true&amp;characterEncoding=UTF8");
            dataSource.setUser("portal");
            dataSource.setPassword("portal");
        } catch (SQLException e) {
            throw new RuntimeException("Problem with creating MariaDB data source.", e);
        }

        return dataSource;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect");
        properties.setProperty("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mariadb://localhost:3306/portal?useUnicode=true&amp;characterEncoding=UTF8");
        properties.setProperty("hibernate.connection.username", "portal");
        properties.setProperty("hibernate.connection.password", "portal");

        DatasourceConnectionProviderImpl connectionProvider = new DatasourceConnectionProviderImpl();
        connectionProvider.setDataSource(dataSource());
        properties.put("hibernate.connection.provider_class", connectionProvider);


        return properties;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionFactoryBuilder.addAnnotatedClass(Product.class);
        sessionFactoryBuilder.setProperties(additionalProperties());

        return sessionFactoryBuilder.buildSessionFactory();
    }

}