package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String name;
    private Wallet wallet;
    private List<Order> orders;
    private Cart cart;
}
