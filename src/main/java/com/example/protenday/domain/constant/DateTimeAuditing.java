package com.example.protenday.domain.constant;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateTimeAuditing {

    @CreatedDate
    @Column(name = "regitered_at", updatable = false)
    public LocalDateTime registeredAt;

    @CreatedBy
    @Column(name = "registered_by", updatable = false)
    public String registeredBy;

    @LastModifiedDate
    @Column(name = "modified_at")
    public LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(name = "modified_by")
    public String modifiedBy;

    @Column(name = "deleted_at")
    public LocalDateTime deletedAt;
}
