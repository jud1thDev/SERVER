package com.efub.leadtoyproject.domain.product.repository;

import com.efub.leadtoyproject.domain.category.domain.ProductType;
import com.efub.leadtoyproject.domain.product.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"category"})
    List<Product> findAll();

    @EntityGraph(attributePaths = {"category"})
    Product findByProductId(Long productId);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.productType = :productType")
    List<Product> findByProductType(ProductType productType);
}
