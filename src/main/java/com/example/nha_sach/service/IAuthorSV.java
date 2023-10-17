package com.example.nha_sach.service;

import com.example.nha_sach.dto.AuthorDTO;

import java.util.List;

public interface IAuthorSV extends IBaseService<AuthorDTO>{
    List<AuthorDTO> getAllListAuthor();
    String checkSizeAuthor(String name);
    List<AuthorDTO> findAuthorById(Long id);
    List<AuthorDTO> findAuthorByName(String name);
    List<AuthorDTO> CheckAndSaveAuthorInProduct(String name);
    String updateCodeAuthor(String name,String code);
}
