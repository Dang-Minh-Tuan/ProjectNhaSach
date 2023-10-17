package com.example.nha_sach.repository;

import com.example.nha_sach.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRP extends JpaRepository<Employee,Long> {
}
