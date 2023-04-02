package com.example.protenday.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "image")
@Entity
public class ImageEntity {

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
