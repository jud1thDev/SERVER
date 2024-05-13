package com.efub.leadtoyproject.item.repository;

import com.efub.leadtoyproject.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
