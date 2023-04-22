package com.ivona.datafood.defaultProducts;

import com.ivona.datafood.product.Product;
import com.ivona.datafood.product.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductsMapper {

    // Change from default, to add to user's product list.
    public Product toProduct(DefaultProductsDto dto) {
        if (dto == null) {
            return null;
        }

        Product entity = new Product();

        entity.setName(dto.getName());
        entity.setType(dto.getType());

        return entity;
    }

    public ProductDto toProductDto(DefaultProductsDto dto) {
        if (dto == null) {
            return null;
        }

        ProductDto dto1 = new ProductDto();

        dto1.setName(dto.getName());
        dto1.setType(dto.getType());

        return dto1;
    }

    public DefaultProducts fromDto(DefaultProductsDto dto) {
        if (dto == null) {
            return null;
        }

        DefaultProducts entity = new DefaultProducts();

        entity.setName(dto.getName());
        entity.setType(dto.getType());

        return entity;
    }

    public DefaultProductsDto toDto(DefaultProducts entity) {
        if (entity == null) {
            return null;
        }

        DefaultProductsDto dto = new DefaultProductsDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());

        return dto;
    }
}
