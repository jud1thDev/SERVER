package com.efub.leadtoyproject.domain.item.repository;

import com.efub.leadtoyproject.domain.item.domain.Item;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByProductProductId(Long productId);

//    @Query("SELECT i FROM Item i JOIN FETCH i.product p JOIN FETCH p.category c WHERE i.store = :store AND c.productType = :productType")
//    List<Item> findItemsByStoreAndProductType(String store, String productType, Pageable pageable);

}
