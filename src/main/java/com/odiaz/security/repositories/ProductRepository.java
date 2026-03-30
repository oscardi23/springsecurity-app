package com.odiaz.security.repositories;

import com.odiaz.security.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


    @Query("SELECT p FROM ProductEntity p WHERE p.active = true")
    List<ProductEntity> findAllActiveProducts();



}
