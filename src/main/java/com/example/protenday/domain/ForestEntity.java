package com.example.protenday.domain;

import com.example.protenday.domain.constant.DateTimeAuditing;
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
@Table(name = "forest")
@SQLDelete(sql = "UPDATE forest SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class ForestEntity extends DateTimeAuditing {

    /** 인덱스 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 유저 아이디 */
    @OneToOne @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    /** 전달받은 메시지 */
    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<MessageEntity> messages = new LinkedHashSet<>();

    public void addMessage(MessageEntity message) {
        message.setForestEntity(this);
        messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForestEntity that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
