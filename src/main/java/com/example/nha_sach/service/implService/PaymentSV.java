package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.PaymentDTO;
import com.example.nha_sach.entities.Payment;
import com.example.nha_sach.mapper.PaymentMP;
import com.example.nha_sach.repository.PaymentRP;
import com.example.nha_sach.service.IPaymentSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentSV implements IPaymentSV {
    @Autowired
    private PaymentRP paymentRP;
    @Autowired
    private PaymentMP paymentMP;

    @Override
    public Page<PaymentDTO> getAll(Pageable pageable) {
        Page<PaymentDTO> paymentDTOS = paymentRP.findAll(pageable).map(x -> paymentMP.toDTO(x));
        return paymentDTOS;
    }

    @Override
    public boolean Save(PaymentDTO paymentDTO) {
        try {
            paymentRP.save(new Payment().toEntity(paymentDTO));
            return true;
        }catch (Exception  e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(PaymentDTO paymentDTO) {
        try {
            paymentRP.save(new Payment().toEntity(paymentDTO));
            return true;
        }catch (Exception  e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(PaymentDTO paymentDTO) {
        paymentRP.delete(new Payment().toEntity(paymentDTO));
        return true;
    }
}
