package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    private String id;
    private Integer amount;
}
