package com.ivona.datafood.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName (String productname);

    @Query("SELECT p FROM Product p WHERE p.type LIKE %:type%")
    List<Product> findByType(@Param("type") String type);
}
