package com.efub.leadtoyproject.domain.item.repository;

import com.efub.leadtoyproject.domain.item.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.itemId IN :itemIds")
    List<Item> findAllByIds(List<Long> itemIds);
}
