package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRP extends JpaRepository<Author,Long> {
    List<Author> findAuthorById(Long id);

    Set<Author> findAuthorByName(String name);
}
