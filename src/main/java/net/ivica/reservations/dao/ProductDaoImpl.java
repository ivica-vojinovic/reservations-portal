package net.ivica.reservations.dao;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.command.ProductSearchCommand;
import net.ivica.reservations.api.dao.ProductDao;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractGenericDao<Product, ProductSearchCommand> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }

}
