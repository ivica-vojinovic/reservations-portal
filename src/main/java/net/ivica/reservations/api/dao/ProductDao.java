package net.ivica.reservations.api.dao;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.command.ProductSearchCommand;

public interface ProductDao extends GenericDao<Product, ProductSearchCommand> {
}

