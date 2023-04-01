package com.example.potenday.repository;

import com.example.potenday.domain.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageEntityRepository extends JpaRepository<MessageEntity, Long> {
}
