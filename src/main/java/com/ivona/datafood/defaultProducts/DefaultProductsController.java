package com.ivona.datafood.defaultProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DefaultProductsController {

    @Autowired
    DefaultProductsService service;

    @Secured("ROLE_ADMIN")
    @PostMapping("/productlist/add")
    public String addProduct(@RequestBody DefaultProductsDto dto) {
        return service.addProduct(dto);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/productlist/get/{id}")
    public DefaultProductsDto getProduct(@PathVariable("id") Integer id) {
        return service.getProduct(id);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/productlist/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @RequestBody DefaultProductsDto dto) {
        return service.updateProduct(id, dto);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/productlist/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        return service.deleteProduct(id);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/productlist/get/all")
    public List<DefaultProductsDto> getAll() {
        return service.getAll();
    }

}
