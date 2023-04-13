package com.example.demo.Controller;

import com.example.demo.Dtos.ProductsDto;
import com.example.demo.Model.Products;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductsController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity<ProductsDto> addProduct(@RequestBody ProductsDto productsDto){
        productService.addProduct(productsDto);
        return new ResponseEntity<>(productsDto, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?>  removeProduct(@RequestParam String name){
        productService.removeProduct(name);
        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Products>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-one{name}")
    public ResponseEntity<Products> getByName(@PathVariable String name){
        return new ResponseEntity<>(productService.getByName(name),HttpStatus.ACCEPTED);
    }
}
