package com.pizza.backend.atb.orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.backend.atb.premade.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        // TODO Auto-generated method stub
        return orderRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order addOrder(String userId, OrderDetail orderDetail) throws JsonProcessingException {
        Order order = new Order();
        order.setUserId(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJSON = objectMapper.writeValueAsString(orderDetail);
        order.setOrderJSON(orderJSON);
        return orderRepository.save(order);
    }

    public List<OrderDto> getOrders(String userId) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Order> orderList = orderRepository.findByUserId(userId);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order: orderList){

            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getOrderId());
            orderDto.setUserId(order.getUserId());
            orderDto.setOrderDetail(objectMapper.readValue(order.getOrderJSON(), OrderDetail.class));
            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }

    public OrderDetail getPrevOrder(String orderId) throws IOException {
        //todo: BUG orderId in the stored JSON object is always 0 orderId shouldn't be part of the JSON string
        Order order = orderRepository.findOne(new Integer(orderId));
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDetail orderDetail = objectMapper.readValue(order.getOrderJSON(), OrderDetail.class);
        return orderDetail;
    }

    public void deleteOrderById(Integer orderId){

        orderRepository.delete(orderId);
    }

}
