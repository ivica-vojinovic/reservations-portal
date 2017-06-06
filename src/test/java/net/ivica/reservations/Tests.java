package net.ivica.reservations;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.dao.ProductDao;
import net.ivica.reservations.api.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql({"/scripts/reset-db.sql", "/scripts/inital-data.sql"})
@ActiveProfiles("tests")
public class Tests {

    @Autowired
    private ProductService _productService;

    @Test
    public void testExample() {
        Product product = _productService.findById(Long.valueOf(1));

        System.out.println(product.getIdentifier());
    }

}
