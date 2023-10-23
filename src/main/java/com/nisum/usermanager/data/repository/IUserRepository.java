package com.nisum.usermanager.data.repository;

import com.nisum.usermanager.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
}
