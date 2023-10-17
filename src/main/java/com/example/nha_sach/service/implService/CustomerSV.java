package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.CustomerDTO;
import com.example.nha_sach.entities.Customer;
import com.example.nha_sach.mapper.CustomerMP;
import com.example.nha_sach.repository.CustomerRP;
import com.example.nha_sach.service.ICustomerSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerSV implements ICustomerSV {
    @Autowired
    private CustomerRP customerRP;
    @Autowired
    private CustomerMP customerMP;

    @Override
    public Page<CustomerDTO> getAll(Pageable pageable) {
        Page<CustomerDTO> customerDTOS = customerRP.findAll(pageable).map(x -> customerMP.toDTO(x));
        return customerDTOS;
    }

    @Override
    public boolean Save(CustomerDTO customerDTO) {
        try {
            customerRP.save(new Customer().toEntity(customerDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(CustomerDTO customerDTO) {
        try {
            customerRP.save(new Customer().toEntity(customerDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(CustomerDTO customerDTO) {
        customerRP.delete(new Customer().toEntity(customerDTO));
        return true;
    }
}
