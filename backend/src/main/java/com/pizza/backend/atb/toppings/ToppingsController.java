package com.pizza.backend.atb.toppings;

import com.pizza.backend.atb.toppings.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class ToppingsController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/toppings", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Toppings get() {

        Toppings toppings = new Toppings();
        toppings.setSizes(Arrays.asList(Size.SMALL,Size.MEDIUM,Size.LARGE));
        toppings.setCheeses(Arrays.asList(Cheese.MOZZARELLA, Cheese.CHEDDAR, Cheese.MONTEREYJACK));
        toppings.setCrusts(Arrays.asList(Crust.ORIGINAL, Crust.GLUTENFREE, Crust.WHOLEWHEAT));
        toppings.setSauces(Arrays.asList(Sauce.TOMATO, Sauce.ALFREDO));
        toppings.setMeats(Arrays.asList(Meat.PEPPERONI, Meat.SAUSAGE, Meat.BACON, Meat.CHICKEN, Meat.HAM, Meat.ITALIANSAUSAGE));
        toppings.setOtherToppings(Arrays.asList(OtherTopping.BANANAPEPPERS
                , OtherTopping.BASIL
                , OtherTopping.BLACKOLIVES
                , OtherTopping.CARAMELIZEDONIONS
                , OtherTopping.FRESHTOMATO
                , OtherTopping.FRIEDEGG
                , OtherTopping.MUSHROOMS
                , OtherTopping.PINEAPPLE));

        return toppings;
    }
}
