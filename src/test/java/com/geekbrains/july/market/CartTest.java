package com.geekbrains.july.market;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CartTest {
    @Autowired
    Cart cart;

    @BeforeEach
    public void cardFill(){
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            long productId = i;
            product.setId(productId);
            product.setTitle("Product #"+productId);
            product.setPrice(new BigDecimal(100 + productId));
            cart.add(product);
        }
    }

    @Test
    public void cartAddTest(){

        Assertions.assertEquals(5, cart.getItems().size());

        for (OrderItem o: cart.getItems()
        ) {
            System.out.println(o.getProduct().getTitle()+ " "+ o.getProduct().getPrice());
        }
        cart.clear();
        Assertions.assertEquals(0, cart.getItems().size());
    }

    @Test
    public void cartSumTest(){
        Assertions.assertEquals((100+101+102+103+104), (int)cart.getPrice().doubleValue());

        long i = 0;
        cart.removeByProductId(i);

        Assertions.assertEquals((101+102+103+104), (int)cart.getPrice().doubleValue());

        cart.clear();
        Assertions.assertEquals(0, (long)cart.getPrice().doubleValue());
    }
}
