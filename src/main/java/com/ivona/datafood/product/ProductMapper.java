package com.ivona.datafood.product;

import com.ivona.datafood.user.User;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductDto toDto(Product entity) {
        if (entity == null) {
            return null;
        }

        ProductDto dto = new ProductDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setExpirationDate(entity.getExpirationDate());
        dto.setUserId(entity.getUser().getId());

        return dto;
    }
    public Product fromDto(ProductDto dto){

        if(dto == null){
            return null;
        }
        Product entity = new Product();

        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setUser(new User());

        return entity;
    }

}

