package com.example.protenday.domain.constant;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateTimeAuditing {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(name = "registered_at", updatable = false)
    public LocalDateTime registeredAt;

    @CreatedBy
    @Column(name = "registered_by", updatable = false)
    public String registeredBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(name = "modified_at")
    public LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(name = "modified_by")
    public String modifiedBy;

    @Column(name = "deleted_at")
    public LocalDateTime deletedAt;
}
