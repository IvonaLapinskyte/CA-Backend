package com.ivona.datafood.product;

import com.ivona.datafood.user.User;
import com.ivona.datafood.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserRepository userRepository;


    @Transactional
    public ResponseEntity<String> addProduct(ProductDto product, String username) {
        if (product == null) {
            return ResponseEntity.badRequest().body("Something went wrong... Try again.");
        }
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("This user does not exist!");
        }

        Product newProduct = productMapper.fromDto(product);
        newProduct.setUser(user);
        productRepo.save(newProduct);

        return ResponseEntity.ok("Product added.");
    }

    public ProductDto getProduct(String productname) {
        if (productname == null) {
            return null;
        }

        Product product = productRepo.findByName(productname);
        if (product == null) {
            return null;
        }
        return productMapper.toDto(product);
    }

    public String updateProduct(Integer id, ProductDto product) {
        if (id == null || product == null) {
            return null;
        }

        Product findProduct = productRepo.findById(id).orElse(null);
        if (findProduct == null) {
            return "Product with id " + id + " was not found!";
        }

        Product transfer = productMapper.fromDto(product);
        transfer.setId(id);
        productRepo.save(transfer);

        return "Product was updated successfully!";
    }

        public String deleteProduct (Integer id){
            if (productRepo.existsById(id)) {
                productRepo.deleteById(id);

                return "Product with id" + id + "deleted succssesfully!";
            } else {
                return "Can not delete non-existing product!";
            }
        }

    public List<ProductDto> getAllByType(String type) {
        List<Product> products = productRepo.findByType(type);

        if (products == null) {
            return null;
        }

        List<ProductDto> dtoProducts = new ArrayList<>();
        products.forEach(product -> dtoProducts.add(productMapper.toDto(product)));

        return dtoProducts;
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> dtoProducts = new ArrayList<>();
        List<Product> products = (List<Product>) productRepo.findAll();
        products.forEach(product -> dtoProducts.add(productMapper.toDto(product)));

        return dtoProducts;
    }
}

