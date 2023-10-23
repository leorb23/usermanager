package com.nisum.usermanager.data.repository;

import com.nisum.usermanager.data.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPhoneRepository extends JpaRepository<PhoneEntity, UUID> {

}
