package net.ivica.reservations.dao;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.command.ProductSearchCommand;
import net.ivica.reservations.api.dao.ProductDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractGenericDao<Product, ProductSearchCommand> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public Long save(Product entity) {
        return null;
    }

    @Override
    public void saveOrUpdate(Product entity) {

    }

    @Override
    public void update(Product entity) {

    }

    @Override
    protected void afterLoadingEntites(List<Product> elements, ProductSearchCommand command) {

    }

}
