package com.example.demo.Controller;

import com.example.demo.Dtos.ProductDto;
import com.example.demo.Dtos.UserDto;
import com.example.demo.Model.Products;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    private static final String REACH_PRODUCT = "http://localhost:8809/Products";
    WebClient webclient = WebClient.create(REACH_PRODUCT);
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getUser(){
        return new ResponseEntity<>(userService.getUser(),HttpStatus.ACCEPTED);
    }
//    @PostMapping("/add-to-cart")
//    public ResponseEntity<String> addToCart(@RequestParam String productName,@RequestParam String userName){
//        return new ResponseEntity<>(userService.addToCart(productName,userName),HttpStatus.ACCEPTED);
//    }
//    @GetMapping("/order-from-cart")
//    public ResponseEntity<String> orderList(@RequestBody List<String> orders,@RequestParam String userName){
//        return new ResponseEntity<>(userService.orderList(orders,userName),HttpStatus.ACCEPTED);
//    }
    @GetMapping("/get-products")
    public ResponseEntity<List<Products>> getAllProducts(){
        return new ResponseEntity<>(webclient.get().uri("/get")
                .retrieve().bodyToFlux(Products.class).collectList().block(),HttpStatus.ACCEPTED);
    }
    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto){
        webclient.post().uri("/add")
                .syncBody(productDto).retrieve().bodyToMono(ProductDto.class).block();
        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }
}

