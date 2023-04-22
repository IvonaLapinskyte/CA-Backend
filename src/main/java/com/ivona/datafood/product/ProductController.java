package com.ivona.datafood.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @Transactional
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/product/add/{username}")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto product, @PathVariable("username") String username) {
        return service.addProduct(product, username);
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/product/get/{productname}")
    public ProductDto getProduct(@PathVariable("productname") String productname){
        return service.getProduct(productname);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PutMapping ("/product/update/{id}")
    public String updateProduct (@PathVariable("id") Integer id, @RequestBody ProductDto product){
        return service.updateProduct(id, product);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @DeleteMapping ("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        return service.deleteProduct(id);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping ("/product/filter/{type}")
    public List<ProductDto> getAllByType(@PathVariable("type") String type) {
        return service.getAllByType(type);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping ("/product/get/all")
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }
}
