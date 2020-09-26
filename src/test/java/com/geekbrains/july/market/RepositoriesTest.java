package com.geekbrains.july.market;


import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
        Product product = new Product(null, "Product X", new BigDecimal(350.0));
        Product out = entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = productsRepository.findAll();

        Assertions.assertEquals(5, productsList.size());
        Assertions.assertEquals("Product X", productsList.get(4).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Product> productsList = productsRepository.findAll();
        Assertions.assertEquals(4, productsList.size());
    }
}
