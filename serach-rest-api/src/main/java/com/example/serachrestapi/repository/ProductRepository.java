package com.example.serachrestapi.repository;

import com.example.serachrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where " +
            "p.name like %:query% " +
            "or p.desc like %:query%")
    List<Product> findByNameAndDescJPQL(@Param("query") String query);

    @Query(value = "select * from products p where " +
            "p.name like concat('%',:query,'%')" +
            "or p.description like concat('%',:query,'%')"
            , nativeQuery = true)
    List<Product> findByNameAndDescSQL(@Param("query") String query);

}