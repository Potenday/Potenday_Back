package com.example.potenday.domain;

import com.example.potenday.domain.constant.DateTimeAuditing;
import com.example.potenday.dto.request.PlantRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plant")
@SQLDelete(sql = "UPDATE plant SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class PlantEntity extends DateTimeAuditing {

    /** 인덱스 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 식물 이름 */
    @Column(nullable = false, length = 100)
    private String name;

    /** 속성 */
    @Column(nullable = false)
    private String attribute;

    /** 식물 설명 */
    @Column(name = "description") String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlantEntity that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    public void updatePlant(PlantRequest request) {
        this.name = Objects.equals(this.name, request.getName()) ? this.name : request.getName();
        this.attribute = Objects.equals(this.attribute, request.getAttribute()) ? this.attribute : request.getAttribute();
        this.description = Objects.equals(this.description, request.getDescription()) ? this.description : request.getDescription();
    }
}
