package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.EmployeeDTO;
import com.example.nha_sach.entities.Employee;
import com.example.nha_sach.mapper.EmployeeMP;
import com.example.nha_sach.repository.EmployeeRP;
import com.example.nha_sach.service.IEmployeeSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSV implements IEmployeeSV {
    @Autowired
    private EmployeeRP employeeRP;
    @Autowired
    private EmployeeMP employeeMP;

    @Override
    public Page<EmployeeDTO> getAll(Pageable pageable) {
        Page<EmployeeDTO> employeeDTOS = employeeRP.findAll(pageable).map(x -> employeeMP.toDTO(x));
        return employeeDTOS;
    }

    @Override
    public boolean Save(EmployeeDTO employeeDTO) {
        try {
            employeeRP.save(new Employee().toEntity(employeeDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(EmployeeDTO employeeDTO) {
        try {
            employeeRP.save(new Employee().toEntity(employeeDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(EmployeeDTO employeeDTO) {
        employeeRP.delete(new Employee().toEntity(employeeDTO));
        return true;
    }
}
