package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.BillDTO;
import com.example.nha_sach.entities.Bill;
import com.example.nha_sach.mapper.BillMP;
import com.example.nha_sach.repository.BillRP;
import com.example.nha_sach.service.IBillSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillSV implements IBillSV {
    @Autowired
    private BillRP billRP;
    @Autowired
    private BillMP billMP;

    @Override
    public Page<BillDTO> getAll(Pageable pageable) {
        Page<BillDTO> billDTOS = billRP.findAll(pageable).map(x -> billMP.toDTO(x));
        return billDTOS;
    }

    @Override
    public boolean Save(BillDTO billDTO) {
        try {
            billRP.save(new Bill().toEntity(billDTO));
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(BillDTO billDTO) {
        try {
            billRP.save(new Bill().toEntity(billDTO));
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(BillDTO billDTO) {
        billRP.delete(new Bill().toEntity(billDTO));
        return true;
    }
}
