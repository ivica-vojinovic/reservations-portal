package net.ivica.reservations.service;

import net.ivica.reservations.AbstractTests;
import net.ivica.reservations.api.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProductServiceTests extends AbstractTests {

    @Test
    public void findAll() throws Exception {
        List<Product> products = getProductService().findAll();

        Assert.assertTrue(products.stream().anyMatch(product -> product.getProductName().equals("Test product")));
        Assert.assertTrue(products.stream().anyMatch(product -> product.getProductName().equals("Find all")));
    }

    @Test
    public void findAndDelete() {
        Product productByName = getProductService().findProductByName("Delete product");

        Assert.assertNotNull(productByName);
        Assert.assertEquals("Delete product", productByName.getProductName());

        getProductService().delete(productByName);

        productByName = getProductService().findProductByName("Delete product");

        Assert.assertNull(productByName);
    }

    @Test
    public void save() {
        Product product = new Product();
        String productDescription = "Sminjkane";
        product.setProductDescription(productDescription);
        product.setProductName(productDescription);

        getProductService().save(product);

        Product savedProduct = getProductService().findById(product.getProductId());

        Assert.assertEquals(productDescription, savedProduct.getProductDescription());
        Assert.assertEquals(productDescription, savedProduct.getProductName());
    }
}
