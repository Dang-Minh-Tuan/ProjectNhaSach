package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.DonHangDTO;
import com.example.nha_sach.entities.DonHang;
import com.example.nha_sach.mapper.DonHangMP;
import com.example.nha_sach.repository.DonHangRP;
import com.example.nha_sach.service.IDonHangSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DonHangSV implements IDonHangSV {
    @Autowired
    private DonHangRP donHangRP;
    @Autowired
    private DonHangMP donHangMP;

    @Override
    public Page<DonHangDTO> getAll(Pageable pageable) {
        Page<DonHangDTO> donHangDTOS = donHangRP.findAll(pageable).map(x -> donHangMP.toDTO(x));
        return donHangDTOS;
    }

    @Override
    public boolean Save(DonHangDTO donHangDTO) {
        try {
            donHangRP.save(new DonHang().toEntity(donHangDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(DonHangDTO donHangDTO) {
        try {
            donHangRP.save(new DonHang().toEntity(donHangDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(DonHangDTO donHangDTO) {
        donHangRP.delete(new DonHang().toEntity(donHangDTO));
        return true;
    }
}
