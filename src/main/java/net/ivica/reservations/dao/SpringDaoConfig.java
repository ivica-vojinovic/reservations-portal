package net.ivica.reservations.dao;

import net.ivica.reservations.api.Product;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class SpringDaoConfig {

    public static final String DB_URL = "jdbc:mariadb://localhost:3306/portal?useUnicode=true&amp;characterEncoding=UTF8";
    public static final String DB_USER = "portal";
    public static final String DB_PASSWORD = "portal";

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect");
        properties.setProperty("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", DB_USER);
        properties.setProperty("hibernate.connection.username", DB_USER);
        properties.setProperty("hibernate.connection.password", DB_PASSWORD);
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");

        DatasourceConnectionProviderImpl connectionProvider = new DatasourceConnectionProviderImpl();
        connectionProvider.setDataSource(dataSource());
        properties.put("hibernate.connection.provider_class", connectionProvider);


        return properties;
    }

    @Bean
    public DataSource dataSource() {
        MariaDbDataSource dataSource = null;
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl(DB_URL);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Problem with creating MariaDB data source.", e);
        }

        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionFactoryBuilder.addAnnotatedClass(Product.class);
        sessionFactoryBuilder.setProperties(additionalProperties());

        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

}