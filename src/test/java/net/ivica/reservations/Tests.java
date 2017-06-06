package net.ivica.reservations;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.dao.ProductDao;
import net.ivica.reservations.api.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql("/scripts/reset-db.sql")
public class Tests {

    @Autowired
    private ProductService _productService;

    @Test
    public void testExample() {
        Product product = new Product();
        product.setProductDescription("Description");

        _productService.save(product);
    }

}
