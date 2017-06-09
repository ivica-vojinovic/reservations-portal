package net.ivica.reservations.api.service;

import net.ivica.reservations.api.Product;

public interface ProductService extends GenericService<Product> {

    Product findProductByName(String productName);

}
