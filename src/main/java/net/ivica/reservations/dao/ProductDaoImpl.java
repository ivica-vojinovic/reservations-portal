package net.ivica.reservations.dao;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.dao.ProductDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public Product findProductByName(String productName) {
        List<Product> products = (List<Product>) getHibernateTemplate().findByNamedQueryAndNamedParam("find_product_by_name", "productName", productName);

        if (products.isEmpty()) {
            return null;
        }

        return products.get(0);
    }
}
