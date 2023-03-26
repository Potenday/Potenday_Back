package com.example.protenday.domain;

import com.example.protenday.domain.constant.DateTimeAuditing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_plant")
@SQLDelete(sql = "UPDATE user_plant SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class UserPlantEntity extends DateTimeAuditing {

    /** 인덱스 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 유저 인덱스 */
    @ManyToOne @JoinColumn(name = "user_id") private UserEntity user;

    /** 식물 인덱스 */
    @ManyToOne @JoinColumn(name = "plant_id") private PlantEntity plant;

    @Column(name = "message", length = 255) private String message;

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setPlant(PlantEntity plant) {
        this.plant = plant;
    }
}
