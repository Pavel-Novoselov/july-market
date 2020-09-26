package com.geekbrains.july.market;

import com.geekbrains.july.market.controllers.ProductsController;
import com.geekbrains.july.market.entities.dtos.ProductDto;
import com.geekbrains.july.market.services.ProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ProductsController productsController;

    @MockBean
    ProductsService productsService;

    List<ProductDto> list = new ArrayList<>();

    @Test
    public void fullRestTest() {
        // List<Product> products = this.restTemplate.getForObject("/api/v1/products", List.class);
        List<ProductDto> books = restTemplate.getForObject("/api/v1/books", List.class);
        System.out.println(books);
        assertThat(books).isNotNull();
    }

//    @BeforeEach
//    public void setUP() throws Exception{
//        for (int i = 0; i < 5; i++) {
//            PrductDto product = new PrductDto();
//            long productId = i;
//            product.setId(productId);
//            product.setTitle("Product #"+productId);
//            product.setPrice(new BigDecimal(100 + productId));
//            list.add(product);
//        }
//
//        productsController = new ProductsController(productsService);
//        when(productsService.findAllDtos()).thenReturn(list);
//    }
//
//    @Test
//    public void getAllProductTest(){
//
////        Mockito.doReturn()
//
//        final List list = this.restTemplate.getForObject("/api/v1/products", List.class);
//
//        Assertions.assertEquals(4, list.size());
//
//
//    }

}
