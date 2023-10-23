package com.nisum.usermanager.data.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -8171033602562273639L;

    @Id
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String token;
    @Column(name = "is_active")
    private boolean isActive;
    private LocalDate created;
    private LocalDate modified;
    @Column(name = "last_login")
    private LocalDate lastLogin;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

}
