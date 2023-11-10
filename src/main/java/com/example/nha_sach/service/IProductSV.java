package com.example.nha_sach.service;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.dto.ProductDTO;

import java.util.List;

public interface IProductSV extends IBaseService<ProductDTO>{
    List<ProductDTO> getAllListProduct();

    String checkSizeProduct(String name);
    List<ProductDTO> findProductById(Long id);
    String updateCodeProduct(String name, String code);

    List<ProductDTO> findProductByIdCategory(Long idCate);
    List<ProductDTO> findProductByIdAuthor(Long idAuth);
    List<ProductDTO> findProductByIdPublisher(Long idPub);
}
