package com.order.status.dao.entity;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
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
