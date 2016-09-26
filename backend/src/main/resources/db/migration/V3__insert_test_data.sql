

INSERT INTO users (user_id, user_email) VALUES ('test','test');

INSERT INTO public.orders(user_id, order_json)
    VALUES ('test', '{"userId":"10157582987105515","orderDate":"","premadeName":"Dutch Delight","size":"medium","crustStyle":"GlutenFree","baseSauce":"tomato","cheese":"Cheddar","meats":["pepperoni","sausage"],"otherToppings":["Basil","BlackOlives","FriedEgg"]}');

INSERT INTO public.orders(user_id, order_json)
    VALUES ('test', '{"userId":"10157582987105515","orderDate":"","premadeName":"Somalian Slice","size":"medium","crustStyle":"WholeWheat","baseSauce":"alfredo","cheese":"Cheddar","meats":["pepperoni"],"otherToppings":["CaramelizedOnions","Mushrooms","Pineapple"]}');


