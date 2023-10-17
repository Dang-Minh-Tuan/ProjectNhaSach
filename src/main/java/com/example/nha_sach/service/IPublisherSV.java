package com.example.nha_sach.service;

import com.example.nha_sach.dto.PublisherDTO;

import java.util.List;

public interface IPublisherSV extends IBaseService<PublisherDTO>{
    public List<PublisherDTO> getAllListPub();
    public String checkSizePub(String name);
    public List<PublisherDTO> findPublisherById(Long id);
    public List<PublisherDTO> findPublishersByName(String name);
    public PublisherDTO CheckAndSavePublisherInProduct(String name);
    String updateCodePub(String name,String code);
}
