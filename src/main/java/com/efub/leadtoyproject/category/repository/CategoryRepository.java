package com.efub.leadtoyproject.category.repository;

import com.efub.leadtoyproject.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
