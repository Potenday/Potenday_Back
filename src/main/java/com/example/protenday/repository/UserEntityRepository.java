package com.example.protenday.repository;

import com.example.protenday.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
