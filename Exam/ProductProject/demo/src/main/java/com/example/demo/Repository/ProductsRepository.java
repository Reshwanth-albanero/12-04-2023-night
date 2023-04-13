package com.example.demo.Repository;

import com.example.demo.Model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductsRepository  extends MongoRepository<Products,String> {
    Products findByName(String name);
}
//public class ProductsRepository {
//    Map<String,Products> productsMap = new HashMap<>();
//    public void save(Products products){
//        productsMap.put(products.getId(), products);
//    }
//
//    public Products findByName(String name) {
//        for(Map.Entry<String ,Products> itr: productsMap.entrySet()){
//            if(itr.getValue().getName().equals(name)){
//                return itr.getValue();
//            }
//        }
//        return null;
//    }
//
//    public void delete(Products products) {
//        for(Map.Entry<String ,Products> itr: productsMap.entrySet()){
//            if(itr.getValue().getName().equals(products.getName())){
//                productsMap.remove(itr.getKey());
//            }
//        }
//    }
//
//    public List<Products> findAll() {
//        List<Products> products = new ArrayList<>();
//        for(Map.Entry<String ,Products> itr: productsMap.entrySet()){
//            products.add(itr.getValue());
//        }
//        return products;
//    }
//}