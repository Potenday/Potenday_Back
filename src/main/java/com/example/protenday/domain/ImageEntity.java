package com.example.protenday.domain;

import com.example.protenday.domain.constant.DateTimeAuditing;
import com.example.protenday.util.ImageUtils;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder
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

    @Setter @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagedata", length = 16_000_000)
    private byte[] imageData;

    public void setPlant(PlantEntity plant) {
        this.plant = plant;
    }

    public void updateImageSource(byte[] imageData) {
        this.imageData = imageData;
    }
}
