package com.pizza.backend.atb.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "3000")
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = "application/json")
    public User post(@RequestBody LoginRequest loginRequest) throws IOException {

        System.out.println("Looking for user with email: " + loginRequest.getAuthToken());
        User user = userService.getUser(loginRequest.getAuthToken());
        return user;
    }

}
