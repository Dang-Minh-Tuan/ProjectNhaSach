package com.example.nha_sach.entities;

import com.example.nha_sach.dto.BillDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date date_created;
    private String code;
    @OneToOne(mappedBy = "bill",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private DonHang donHang;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bill")
    private Customer customer;
    public Bill toEntity(BillDTO billDTO){
        return Bill.builder().id(billDTO.getId())
                             .name(billDTO.getName())
                             .date_created(billDTO.getDate_created())
                             .code(billDTO.getCode())
                             .donHang(new DonHang().toEntity( billDTO.getDonHangDTO()))
                             .build();
    }
}
