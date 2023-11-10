package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Category;
import com.example.nha_sach.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRP extends JpaRepository<Product,Long> {
    List<Product> findProductById(Long id);
}
