package net.ivica.reservations.service;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.dao.GenericDao;
import net.ivica.reservations.api.dao.ProductDao;
import net.ivica.reservations.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl extends AbstractGenericService<Product> implements ProductService {

    private ProductDao _productDao;

    @Override
    public Product findProductByName(String productName) {
        return getProductDao().findProductByName(productName);
    }

    @Override
    protected GenericDao<Product> getEntityDao() {
        return _productDao;
    }

    private ProductDao getProductDao() {
        return _productDao;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        _productDao = productDao;
    }

}
