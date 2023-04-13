package com.example.demo.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private User user;
    private List<String> products;
}
