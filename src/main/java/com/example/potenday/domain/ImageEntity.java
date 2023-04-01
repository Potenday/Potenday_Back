package com.example.potenday.domain;

import com.example.potenday.domain.constant.DateTimeAuditing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "image")
@SQLDelete(sql = "UPDATE image SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
@Entity
public class ImageEntity extends DateTimeAuditing {

    /** 인덱스 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 식물 인덱스 */
    @OneToOne @JoinColumn(name = "plant_id")
    private PlantEntity plant;

    @Column(name = "filename", columnDefinition = "TEXT")
    private String fileName;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagedata", length = 16_000_000)
    private byte[] imageData;

    public void setPlant(PlantEntity plant) {
        this.plant = plant;
    }

    public void updateImageSource(byte[] imageData) {
        this.imageData = imageData;
    }
}
