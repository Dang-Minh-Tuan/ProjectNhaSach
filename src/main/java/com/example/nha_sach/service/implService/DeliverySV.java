package com.example.nha_sach.service.implService;
import com.example.nha_sach.dto.DeliveryDTO;
import com.example.nha_sach.entities.Delivery;
import com.example.nha_sach.mapper.DeliveryMP;
import com.example.nha_sach.repository.DeliveryRP;
import com.example.nha_sach.service.IDeliverySV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliverySV implements IDeliverySV {
    @Autowired
    private DeliveryRP deliveryRP;
    @Autowired
    private DeliveryMP deliveryMP;

    @Override
    public Page<DeliveryDTO> getAll(Pageable pageable) {
        Page<DeliveryDTO> deliveryDTOS = deliveryRP.findAll(pageable).map(x -> deliveryMP.toDTO(x));
        return deliveryDTOS;
    }

    @Override
    public boolean Save(DeliveryDTO deliveryDTO) {
        try {
            deliveryRP.save(new Delivery().toEntity(deliveryDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(DeliveryDTO deliveryDTO) {
        try {
            deliveryRP.save(new Delivery().toEntity(deliveryDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(DeliveryDTO deliveryDTO) {
        deliveryRP.delete(new Delivery().toEntity(deliveryDTO));
        return true;
    }
}
