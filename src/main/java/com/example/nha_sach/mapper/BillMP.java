package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.BillDTO;
import com.example.nha_sach.entities.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillMP {
    public BillDTO toDTO(Bill bill){
        return BillDTO.builder().id(bill.getId())
                                .name(bill.getName())
                                .date_created(bill.getDate_created())
                                .code(bill.getCode())
                                .donHangDTO(new DonHangMP().toDTO(bill.getDonHang()))
                                .build();
    }
}
