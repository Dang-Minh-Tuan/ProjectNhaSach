package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Bill;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BillDTO {
    private long id;
    private String name;
    private Date date_created;
    private String code;
    private DonHangDTO donHangDTO;

    public Bill toEntity(BillDTO billDTO){
        return Bill.builder().id(billDTO.getId())
                             .name(billDTO.getName())
                             .date_created(billDTO.getDate_created())
                             .code(billDTO.getCode())
                             .build();
    }
}
