package net.ivica.reservations.dao;

import net.ivica.reservations.api.ParameterTuple;
import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.dao.ProductDao;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public Product findProductByName(String productName) {
        return findSingleResult("product_find_by_name", new ParameterTuple("productName", productName));
    }

}
