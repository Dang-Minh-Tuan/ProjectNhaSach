package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRP extends JpaRepository<Bill,Long> {
}
