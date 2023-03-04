package com.netology.authorization.controller;

import com.netology.authorization.entity.Authorities;
import com.netology.authorization.entity.User;
import com.netology.authorization.exceptions.InvalidCredentials;
import com.netology.authorization.exceptions.UnauthorizedUser;
import com.netology.authorization.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class AuthorizationController {
    private UserService service;

    public AuthorizationController(UserService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAutorities(@Valid User user) {
        return service.getAuthorities(user);
    }


    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentials e) {
        System.out.println("Exception: " + e.getMessage());
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handleInvalidUser(UnauthorizedUser e) {
        System.out.println("Exception: " + e.getMessage());
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
