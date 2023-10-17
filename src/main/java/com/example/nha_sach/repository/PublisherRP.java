package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRP extends JpaRepository<Publisher,Long> {
    List<Publisher> findPublisherById(Long id);

    List<Publisher> findPublishersByName(String name);
}
