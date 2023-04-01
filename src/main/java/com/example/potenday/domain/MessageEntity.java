package com.example.potenday.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
@SQLDelete(sql = "UPDATE message SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class MessageEntity {

    /** 인덱스 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 작성자 */
    @ManyToOne @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    /** 받는이 */
    @ManyToOne @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    /** 식물 아이디 */
    @OneToOne @JoinColumn(name = "plant_id") private PlantEntity plant;

    /** 전달될 메시지 내용 */
    @Column(columnDefinition = "TEXT") private String message;

    /** 전달될 숲의 아이디 */
    @Setter @ManyToOne @JoinColumn(name = "forest_id") private ForestEntity forestEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageEntity that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
