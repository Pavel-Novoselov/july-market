package com.geekbrains.july.market;

import com.geekbrains.july.market.entities.dtos.OrderItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<OrderItemDto> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);
       orderItemDto.setProductTitle("Title");
       orderItemDto.setPrice(new BigDecimal(100));
        assertThat(this.jackson.write(orderItemDto)).hasJsonPathNumberValue("$.id");
        assertThat(this.jackson.write(orderItemDto)).extractingJsonPathStringValue("$.productTitle").isEqualTo("Title");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"productTitle\":\"Title\"}";
        OrderItemDto realOI = new OrderItemDto();
        realOI.setId(2L);
        realOI.setProductTitle("Title");

        assertThat(this.jackson.parse(content)).isEqualTo(realOI);
        assertThat(this.jackson.parseObject(content).getProductTitle()).isEqualTo("Title");
    }
}