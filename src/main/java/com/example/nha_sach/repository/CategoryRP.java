package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRP extends JpaRepository<Category,Long> {

    List<Category> findCategoriesById(Long id);
    List<Category> findCategoryByName(String name);
}
