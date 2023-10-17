package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRP extends JpaRepository<Payment,Long> {
}
