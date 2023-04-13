package com.example.demo.Service;

import com.example.demo.Dtos.UserDto;
import com.example.demo.Model.Products;
import com.example.demo.Model.User;
import com.example.demo.Model.Wallet;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public static final String BASE_URL="http://localhost:8809/Product";
    WebClient webClient = WebClient.create(BASE_URL);

//    public ResponseEntity getAllProducts(){
//        ResponseEntity responseEntity = webClient.get()
//                .uri("/get")
//                .retrieve()
//                .toEntity(List.class)
//                .block();
//        return responseEntity;
//    }
    public Flux<Products> findAll(){
        return webClient.get()
            .uri("/get")
            .retrieve()
            .bodyToFlux(Products.class);
    }
    public Products findByName(String name){
        return webClient.get().uri("/get-one"+name).retrieve().bodyToMono(Products.class).block();
    }

    public void addUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        Wallet wallet = new Wallet();
        wallet.setAmount(1000);
        userRepository.save(user);
    }

    public List<UserDto> getUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user: userList){
            userDtoList.add(new UserDto(user.getName()));
        }
        return userDtoList;
    }

//    public String addToCart(String productName,String userName) {
//        Flux<Products> products = findAll();
//        User user = userRepository.findByName(userName);
//        user.getCart().getProductsList().add(products);
//        return "Success";
//    }

    public String orderList(List<String> orders,String userName) {
        User user = userRepository.findByName(userName);
        for(String str: orders){
            Products products = findByName(str);
            if(user.getCart().getProductsList().indexOf(str)>-1){
                if(user.getWallet().getAmount()>products.getPrice()){
                    user.getWallet().setAmount(user.getWallet().getAmount()-products.getPrice());
                }
                else{
                    return "you don't have sufficient amount in your wallet";
                }
            }
            else{
                return "Your order not in the cart so fist add that to the cart";
            }
        }
        return "Success";
    }
}
