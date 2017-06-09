package net.ivica.reservations.api.dao;

import net.ivica.reservations.api.Product;

public interface ProductDao extends GenericDao<Product> {

    Product findProductByName(String productName);

}

