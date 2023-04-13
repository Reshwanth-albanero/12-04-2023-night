package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private List<String> productsList;
}
