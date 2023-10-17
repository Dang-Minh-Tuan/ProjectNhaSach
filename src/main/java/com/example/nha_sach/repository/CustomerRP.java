package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRP extends JpaRepository<Customer,Long> {
}
