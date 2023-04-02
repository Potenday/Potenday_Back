package com.example.protenday.service;

import com.example.protenday.domain.ForestEntity;
import com.example.protenday.domain.UserEntity;
import com.example.protenday.dto.Forest;
import com.example.protenday.exception.ErrorCode;
import com.example.protenday.exception.PotendayApplicationException;
import com.example.protenday.repository.ForestEntityRepository;
import com.example.protenday.util.Base62Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ForestEntityService {

    private final ForestEntityRepository forestEntityRepository;
    private final Base62Utils base62Utils;
    @Value("${front-base-url}")
    private String FRONT_BASE_URL;

    public Forest searchForest(String encodedId) {
        Long decodedId = base62Utils.decodeDirectionId(encodedId);
        ForestEntity forestEntity = forestEntityRepository.findById(decodedId).orElseThrow(() ->
                new PotendayApplicationException(ErrorCode.FOREST_NOT_FOUND)
        );

        return Forest.fromEntity(forestEntity);
    }

    public String createForest(UserEntity userEntity) {
        ForestEntity forestEntity = forestEntityRepository.save(ForestEntity.builder().userEntity(userEntity).build());

        String encodedId = base62Utils.encodeDirectionId(forestEntity.getId());

        return UriComponentsBuilder.fromHttpUrl(FRONT_BASE_URL + "forest/" + encodedId).toUriString();
    }

    public String getMyForest(UserEntity userEntity) {
        ForestEntity forestEntity = forestEntityRepository.findByUserEntity_Id(userEntity.getId());

        String encodedId = base62Utils.encodeDirectionId(forestEntity.getId());

        return UriComponentsBuilder.fromHttpUrl(FRONT_BASE_URL + encodedId).toUriString();
    }

    public String convertURL(String encodedId) {
        return UriComponentsBuilder.fromHttpUrl(FRONT_BASE_URL + "forest/" + encodedId).toUriString();
    }
}
