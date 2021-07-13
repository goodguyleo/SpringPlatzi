package com.platzi.cursoSpring.web.controller;

import com.platzi.cursoSpring.domain.Product;
import com.platzi.cursoSpring.domain.service.ProducrService;
import com.platzi.cursoSpring.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductControler {

    @Autowired
    private ProducrService productService;

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{categoriId}")
    public Optional<List<Product>> getByCategory(@PathVariable("categoriId") int categoryId){
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId){
        return productService.delete(productId);
    }
}
