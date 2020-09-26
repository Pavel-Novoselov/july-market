package com.geekbrains.july.market;

import com.geekbrains.july.market.configs.SecurityConfig;
import com.geekbrains.july.market.controllers.ProductsController;
import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.services.ProductsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(controllers = ProductsController.class, excludeAutoConfiguration = SecurityConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class OnlyProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsService productsService;

    @Test
    public void getAllProductsTest() throws Exception {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        Product product = factory.createProjection(Product.class);
        product.setTitle("Harry Potter");
        List<Product> allProducts = Arrays.asList(
                product
        );
        given(productsService.findAll()).willReturn(allProducts);
        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title", Matchers.is(allProducts.get(0).getTitle())));
    }
}
