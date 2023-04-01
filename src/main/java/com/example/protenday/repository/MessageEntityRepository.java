package com.example.protenday.repository;

import com.example.protenday.domain.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageEntityRepository extends JpaRepository<MessageEntity, Long> {
}
