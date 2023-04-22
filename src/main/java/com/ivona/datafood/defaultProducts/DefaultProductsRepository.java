package com.ivona.datafood.defaultProducts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultProductsRepository extends CrudRepository<DefaultProducts, Integer> {

}
