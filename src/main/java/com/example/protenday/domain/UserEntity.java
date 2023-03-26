package com.example.protenday.domain;

import com.example.protenday.domain.constant.DateTimeAuditing;
import com.example.protenday.domain.constant.Role;
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
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class UserEntity extends DateTimeAuditing {

    /** 인덱스 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 이메일 */
    @Column(nullable = false, name = "email") private String email;

    /** 비밀번호 */
    @Column(nullable = false, name = "password") private String password;

    /** 이름 */
    @Column(nullable = false, name = "fullname",length = 100) private String fullname;

    /** 닉네임 */
    @Column(name = "nickname", length = 100) private String nickname;

    /** 권한 */
    @Column(name = "role") @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Role> roles = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<UserPlantEntity> userPlants = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
