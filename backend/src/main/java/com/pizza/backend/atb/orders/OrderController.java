package com.pizza.backend.atb.orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pizza.backend.atb.orders.request.DeleteOrderRequest;
import com.pizza.backend.atb.premade.OrderDetail;
import com.pizza.backend.atb.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    //todo: Remove test URL http://localhost:8080/
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders;
    }

    @RequestMapping(value = "/order",
            method = RequestMethod.POST,
            produces = "application/json")
    public void post(@RequestBody OrderDetail orderDetail) throws JsonProcessingException {
        Order order = orderService.addOrder(orderDetail.getUserId(), orderDetail);
        System.out.println("OrderDetail saved:" + order);
        return;
    }

    //todo: Remove test URL http://localhost:8080/getOrders/nenadm
    @RequestMapping("/getOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable("userId") String userId) throws IOException {

        if (!userService.userExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<OrderDto> orderDetailList = orderService.getOrders(userId);

        return ResponseEntity
                .ok()
                .body(orderDetailList);
    }

    @RequestMapping("/getPrevOrder/{orderId}")
    //todo: Remove test URL http://localhost:8080/getPrevOrder/10
    public OrderDetail getPrevOrder(@PathVariable("orderId") String orderId) throws IOException {
        OrderDetail orderDetail = orderService.getPrevOrder(orderId);
        System.out.println("OrderDetail found:" + orderDetail.toString());
        return orderDetail;
    }


    @RequestMapping(value = "/deleteorder",
            method = RequestMethod.POST,
            produces = "application/json")
    public void delete(@RequestBody DeleteOrderRequest deleteOrderRequest) throws IOException {

        String isValid = userService.isAuthTokenValid(deleteOrderRequest.getFbAuthToken());

        if(!isValid.isEmpty()){
            orderService.deleteOrderById(Integer.parseInt(deleteOrderRequest.getOrderId()));
        }

        return;
    }
}
