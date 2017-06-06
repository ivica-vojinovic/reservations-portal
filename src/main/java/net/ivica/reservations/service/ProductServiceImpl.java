package net.ivica.reservations.service;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.command.ProductSearchCommand;
import net.ivica.reservations.api.dao.GenericDao;
import net.ivica.reservations.api.dao.ProductDao;
import net.ivica.reservations.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl extends AbstractGenericService<Product, ProductSearchCommand> implements ProductService {

    @Autowired
    private ProductDao _productDao;

    @Override
    protected GenericDao<Product, ProductSearchCommand> getEntityDao() {
        return _productDao;
    }

    private ProductDao getProductDao() {
        return _productDao;
    }

}
