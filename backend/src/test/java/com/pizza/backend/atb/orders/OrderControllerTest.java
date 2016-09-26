package com.pizza.backend.atb.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.backend.atb.premade.OrderDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private OrderService orderService;

    private MockMvc mockMvc;

    private static final String TEST_USER_ID = "test";
    private static final String BAD_USER_ID = "totallynotreal77";


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturn200Ok_whenOrdersFound() throws Exception {

        this.mockMvc.perform(get("/getOrders/" + TEST_USER_ID))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.[0].orderId").exists())
                .andExpect(jsonPath("$.[0].userId").value("test"))
                .andExpect(jsonPath("$.[0].orderDetail.premadeName").value("Dutch Delight"))

                .andExpect(jsonPath("$.[1].orderId").exists())
                .andExpect(jsonPath("$.[1].userId").value("test"))
                .andExpect(jsonPath("$.[1].orderDetail.premadeName").value("Somalian Slice"));
    }

    @Test
    public void shouldReturn404NoContent_whenUserNotFound() throws Exception {

        this.mockMvc.perform(get("/getOrders/" + BAD_USER_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn200OkAndInsert_whenOrdersPosted() throws Exception {

        String name = new Date().toString();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUserId(TEST_USER_ID);
        orderDetail.setPremadeName(name);

        this.mockMvc.perform(post("/order")
                .content(new ObjectMapper().writeValueAsString(orderDetail))
                .header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andExpect(status().isOk());

        List<OrderDto> orders = orderService.getOrders(TEST_USER_ID);

        Integer orderId = null;
        boolean hasMatch = false;

        for (OrderDto orderDto : orders) {
            if (orderDto.getOrderDetail().getPremadeName().equalsIgnoreCase(name)) {
                orderId = orderDto.getOrderId();
                hasMatch = true;
            }
        }

        assertThat(hasMatch).isTrue();

        //cleanup
        if (hasMatch) {
            orderService.deleteOrderById(orderId);
        }
    }
}