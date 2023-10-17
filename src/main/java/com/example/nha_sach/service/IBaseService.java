package com.example.nha_sach.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService <T>{
    Page<T> getAll(Pageable pageable);
    boolean Save(T t);
    boolean Update(T t);
    boolean Delete(T t);
}
