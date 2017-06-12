package net.ivica.reservations.dao;

import net.ivica.reservations.api.Product;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ConfigurationProperties("portal")
public class SpringDaoConfig {

    private String _dbUrl;
    private String _dbUser;
    private String _dbPassword;

    private String _hibernateAuto;
    private String _hibernateImportFiles;

    public String getHibernateImportFiles() {
        return _hibernateImportFiles;
    }

    public void setHibernateImportFiles(String hibernateImportFiles) {
        _hibernateImportFiles = hibernateImportFiles;
    }

    private String _hibernateDialect;
    private String _hibernateDriver;
    private String _hibernateContextClass;

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", getHibernateDialect());
        properties.setProperty("hibernate.connection.driver_class", getHibernateDialect());
        properties.setProperty("hibernate.connection.url", getDbUrl());
        properties.setProperty("hibernate.connection.username", getDbUser());
        properties.setProperty("hibernate.connection.password", getDbPassword());
        properties.setProperty("hibernate.current_session_context_class", getHibernateContextClass());
        properties.setProperty("hibernate.hbm2ddl.auto", getHibernateAuto());

        // This is null when in production sine hbm2dll.auto is set to validate.
        if(getHibernateImportFiles() != null) {
            properties.setProperty("hibernate.hbm2ddl.import_files", getHibernateImportFiles());
            properties.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
        }

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
            dataSource.setUrl(getDbUrl());
            dataSource.setUser(getDbUser());
            dataSource.setPassword(getDbPassword());
        } catch (SQLException e) {
            throw new RuntimeException("Problem with creating MariaDB data source.", e);
        }

        return dataSource;
    }

    public String getDbPassword() {
        return _dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        _dbPassword = dbPassword;
    }

    public String getDbUrl() {
        return _dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        _dbUrl = dbUrl;
    }

    public String getDbUser() {
        return _dbUser;
    }

    public void setDbUser(String dbUser) {
        _dbUser = dbUser;
    }

    public String getHibernateAuto() {
        return _hibernateAuto;
    }

    public void setHibernateAuto(String hibernateAuto) {
        _hibernateAuto = hibernateAuto;
    }

    public String getHibernateContextClass() {
        return _hibernateContextClass;
    }

    public void setHibernateContextClass(String hibernateContextClass) {
        _hibernateContextClass = hibernateContextClass;
    }

    public String getHibernateDialect() {
        return _hibernateDialect;
    }

    public void setHibernateDialect(String hibernateDialect) {
        _hibernateDialect = hibernateDialect;
    }

    public String getHibernateDriver() {
        return _hibernateDriver;
    }

    public void setHibernateDriver(String hibernateDriver) {
        _hibernateDriver = hibernateDriver;
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