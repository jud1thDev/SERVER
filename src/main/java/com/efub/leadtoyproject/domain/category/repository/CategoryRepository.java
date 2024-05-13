package com.efub.leadtoyproject.domain.category.repository;

import com.efub.leadtoyproject.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
