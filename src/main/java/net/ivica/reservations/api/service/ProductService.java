package net.ivica.reservations.api.service;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.command.ProductSearchCommand;

public interface ProductService extends GenericService<Product, ProductSearchCommand> {
}
