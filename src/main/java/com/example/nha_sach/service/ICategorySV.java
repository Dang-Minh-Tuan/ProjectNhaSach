package com.example.nha_sach.service;

import com.example.nha_sach.dto.CategoryDTO;

import java.util.List;

public interface ICategorySV extends IBaseService<CategoryDTO>{
    List<CategoryDTO> getAllListCate();
    String checkSizeCate(String name);
    List<CategoryDTO> findCategoriesById(Long id);
    List<CategoryDTO> findCategoryByName(String name);
    String updateCodeCate(String name,String code);
}
