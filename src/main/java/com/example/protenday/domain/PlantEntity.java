package com.example.protenday.domain;

import com.example.protenday.domain.constant.DateTimeAuditing;
import com.example.protenday.domain.constant.PlantType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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

    /** 식물 타입 */
    @Column(nullable = false, length = 100) @Enumerated(EnumType.STRING)
    private PlantType type;

    /** 식물 설명 */
    @Column(name = "description") String description;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plant", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<UserPlantEntity> userPlants = new LinkedHashSet<>();

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
}
