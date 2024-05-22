package com.efub.leadtoyproject.domain.product.repository;

import com.efub.leadtoyproject.domain.category.domain.ProductType;
import com.efub.leadtoyproject.domain.item.domain.Item;
import com.efub.leadtoyproject.domain.product.domain.Product;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"category"})
    List<Product> findAll();

    @EntityGraph(attributePaths = {"category"})
    Product findByProductId(Long productId);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.productType = :productType")
    List<Product> findByProductType(ProductType productType);

    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN FETCH p.items i " +
            "JOIN FETCH p.category c " +
            "WHERE i.store = :store AND c.productType = :productType " +
            "ORDER BY p.productId DESC")
    List<Product> findProductsByStoreAndProductType(@Param("store") String store,
                                                    @Param("productType") ProductType productType);
}
