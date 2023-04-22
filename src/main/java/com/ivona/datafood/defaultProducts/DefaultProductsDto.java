package com.ivona.datafood.defaultProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultProductsDto {
    private Integer id;
    private String name;
    private String type;
}
