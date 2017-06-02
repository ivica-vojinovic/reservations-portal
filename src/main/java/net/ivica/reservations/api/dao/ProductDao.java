package net.ivica.reservations.api.dao;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.command.ProductSearchCommand;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface ProductDao extends GenericDao<Product, ProductSearchCommand> {
}

