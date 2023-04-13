package com.example.demo.Service;

import com.example.demo.Dtos.ProductsDto;
import com.example.demo.Model.Products;
import com.example.demo.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductsRepository productsRepository;
    public void addProduct(ProductsDto productsDto) {
        Products products = new Products();
        products.setName(productsDto.getName());
        products.setPrice(productsDto.getPrice());
        productsRepository.save(products);
    }

    public void removeProduct(String name) {
        Products products = productsRepository.findByName(name);
        productsRepository.delete(products);
    }

    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    public Products getByName(String name) {
        return productsRepository.findByName(name);
    }
}
