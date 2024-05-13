package com.efub.leadtoyproject.domain.item.repository;

import com.efub.leadtoyproject.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
