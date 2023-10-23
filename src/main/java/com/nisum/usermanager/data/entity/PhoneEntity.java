package com.nisum.usermanager.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "phones")
@Data
public class PhoneEntity implements Serializable {
    private static final long serialVersionUID = 6029576896947591751L;

    @Id
    private UUID id = UUID.randomUUID();
    private String number;
    private String cityCode;
    private String countryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
