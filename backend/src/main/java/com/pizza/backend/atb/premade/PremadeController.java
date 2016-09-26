package com.pizza.backend.atb.premade;

import com.pizza.backend.atb.toppings.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class PremadeController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/premade", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<OrderDetail> get() {

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setPremadeName("Dutch Delight");
        orderDetail1.setSize(Size.MEDIUM);
        orderDetail1.setBaseSauce(Sauce.TOMATO);
        orderDetail1.setCheese(Cheese.CHEDDAR);
        orderDetail1.setCrustStyle(Crust.GLUTENFREE);
        orderDetail1.setMeats(Arrays.asList(Meat.PEPPERONI, Meat.SAUSAGE));
        orderDetail1.setOrderDate(new Date().toString());
        orderDetail1.setOtherToppings(Arrays.asList(OtherTopping.BASIL, OtherTopping.BLACKOLIVES, OtherTopping.FRIEDEGG));

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setPremadeName("Somalian Slice");
        orderDetail2.setSize(Size.MEDIUM);
        orderDetail2.setBaseSauce(Sauce.ALFREDO);
        orderDetail2.setCheese(Cheese.CHEDDAR);
        orderDetail2.setCrustStyle(Crust.WHOLEWHEAT);
        orderDetail2.setMeats(Arrays.asList(Meat.PEPPERONI));
        orderDetail2.setOrderDate(new Date().toString());
        orderDetail2.setOtherToppings(Arrays.asList(OtherTopping.CARAMELIZEDONIONS, OtherTopping.MUSHROOMS, OtherTopping.PINEAPPLE));

        OrderDetail orderDetail3 = new OrderDetail();
        orderDetail3.setPremadeName("Hawaiian");
        orderDetail3.setSize(Size.MEDIUM);
        orderDetail3.setBaseSauce(Sauce.TOMATO);
        orderDetail3.setCheese(Cheese.MOZZARELLA);
        orderDetail3.setCrustStyle(Crust.ORIGINAL);
        orderDetail3.setMeats(Arrays.asList(Meat.HAM));
        orderDetail3.setOrderDate(new Date().toString());
        orderDetail3.setOtherToppings(Arrays.asList(OtherTopping.PINEAPPLE, OtherTopping.BASIL, OtherTopping.FRESHTOMATO));

        return Arrays.asList(orderDetail1, orderDetail2, orderDetail3);
    }


}
