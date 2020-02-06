package com.verification.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;
    private Integer code;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private UserEntity userEntity;
}
