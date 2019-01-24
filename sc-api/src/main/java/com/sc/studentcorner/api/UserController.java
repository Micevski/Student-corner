package com.sc.studentcorner.api;

import com.sc.studentcorner.model.User;
import com.sc.studentcorner.model.request.UserRequest;
import com.sc.studentcorner.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }


    @PostMapping("/register")
    public User saveUser(@RequestBody UserRequest request){
        return service.saveUser(request.email, request.password);
    }

    @GetMapping("/loggedUser")
    public User getLoggedUser(Authentication authentication) {
        return (User)authentication.getPrincipal();
    }
}
