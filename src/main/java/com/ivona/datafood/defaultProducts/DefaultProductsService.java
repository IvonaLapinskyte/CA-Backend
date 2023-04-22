package com.ivona.datafood.defaultProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultProductsService {

    @Autowired
    DefaultProductsRepository productRepo;
    @Autowired
    DefaultProductsMapper productMapper;


    public String addProduct(DefaultProductsDto product) {
        if (product == null) {
            return null;
        }
        productRepo.save(productMapper.fromDto(product));

        return "Product saved.";
    }

    public DefaultProductsDto getProduct(Integer id) {
        if (id == null) {
            return null;
        }
        DefaultProducts product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return productMapper.toDto(product);
    }

    public String updateProduct(Integer id, DefaultProductsDto product) {
        if (id == null || product == null) {
            return null;

        }
        DefaultProducts originalProduct = productRepo.findById(id).orElse(null);
        if (originalProduct == null) {
            return "Product with id " + id + " was not found!";
        }
        DefaultProducts newProduct = productMapper.fromDto(product);
        newProduct.setId(id);
        productRepo.save(newProduct);
        return "Product was updated successfully!";
    }

    public String deleteProduct(Integer id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Product with id" + id + "deleted succssesfully!";
        } else {
            return "Can not delete non-existing product!";
        }
    }

    public List<DefaultProductsDto> getAll() {

        List<DefaultProducts> products = (List<DefaultProducts>) productRepo.findAll();
        List<DefaultProductsDto> dtoProducts = new ArrayList<>();

        products.forEach(product -> dtoProducts.add(productMapper.toDto(product)));

        return dtoProducts;
    }
}
