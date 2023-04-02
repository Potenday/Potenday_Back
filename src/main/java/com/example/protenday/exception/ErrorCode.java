package com.example.protenday.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is invalid"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "Permission is invalid"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    IMAGE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Image not founded"),
    IMAGE_IS_NULL(HttpStatus.NO_CONTENT, "Image is null"),
    INVALID_REFRESH_TOKEN(HttpStatus.CONFLICT, "Refresh Token is invalid"),
    FOREST_NOT_FOUND(HttpStatus.NOT_MODIFIED, "Forest is not founded"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    DUPLICATE_PLANT(HttpStatus.CONFLICT, "Already registered Plant")
    ;

    private HttpStatus status;
    private String message;
}
