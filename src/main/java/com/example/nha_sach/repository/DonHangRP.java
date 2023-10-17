package com.example.nha_sach.repository;

import com.example.nha_sach.entities.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonHangRP extends JpaRepository<DonHang,Long> {
}
