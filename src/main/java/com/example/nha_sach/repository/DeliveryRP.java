package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRP extends JpaRepository<Delivery,Long> {
}
