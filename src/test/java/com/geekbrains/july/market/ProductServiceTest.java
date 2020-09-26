package com.geekbrains.july.market;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.repositories.ProductsRepository;
import com.geekbrains.july.market.services.ProductsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductsService productsService;

    @MockBean
    private ProductsRepository productsRepository;

    List<Product> list = new ArrayList<>();

    @BeforeEach
    public void init(){
        for (int i = 0; i < 5; i++) {
            list.add(new Product(1L, "Product #"+i, new BigDecimal(100+i+10)));
        }
    }

    @Test
    public void findOneProduct(){

        Product productFromDB = new Product(5L, "Title", new BigDecimal(100));

        Mockito.doReturn(Optional.of(productFromDB))
               .when(productsRepository)
               .findById(5L);

        Product productT = productsService.findById(5L).get();

        Assertions.assertNotNull(productT);

        Mockito.verify(productsRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(5L));
    }

    @Test
    public void getAllProducts(){
        Mockito.doReturn(list)
               .when(productsRepository)
               .findAll();

        List<Product> productList = productsService.findAll();

        Assertions.assertEquals(5, productList.size());
    }

}
