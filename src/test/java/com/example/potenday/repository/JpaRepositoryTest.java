package com.example.potenday.repository;

import com.example.potenday.domain.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final UserEntityRepository userEntityRepository;

    public JpaRepositoryTest(@Autowired UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @DisplayName("회원 정보 select 테스트")
    @Test
    void given_when_then() {
        // Given

        // When
        List<UserEntity> userEntities = userEntityRepository.findAll();

        // Then
        System.out.println("JpaRepositoryTest: " + userEntities.size());
   }

    @DisplayName("회원 정보 insert 테스트")
    @Test
    void givenUserAccount_whenInserting_thenWorksFine() {
        // Given
        long previousCount = userEntityRepository.count();
        UserEntity userEntity = UserEntity.builder()
                .email("test@email.com")
                .password("pw")
                .fullname("fullname")
                .nickname("nickname")
                .build();

        // When
        userEntityRepository.save(userEntity);

        // Then
        assertThat(userEntityRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("회원 정보 update 테스트")
    @Test
    void givenUserAccountAndRoleType_whenUpdating_thenWorksFine() {
        // Given
        UserEntity userEntity = userEntityRepository.getReferenceById(1L);
        userEntity.updatePassword("newPassword");

        // When
        UserEntity updatedAccount = userEntityRepository.saveAndFlush(userEntity);

        // Then
        assertThat(updatedAccount)
                .hasFieldOrPropertyWithValue("password", "newPassword");
    }

    @DisplayName("회원 정보 delete 테스트")
    @Test
    void givenUserAccount_whenDeleting_thenWorksFine() {
        // Given
        long previousCount = userEntityRepository.count();
        UserEntity userAccount = userEntityRepository.getReferenceById(1L);

        // When
        userEntityRepository.delete(userAccount);

        // Then
        assertThat(userEntityRepository.count()).isEqualTo(previousCount - 1);
    }

    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig{
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("admin");
        }
    }
}
