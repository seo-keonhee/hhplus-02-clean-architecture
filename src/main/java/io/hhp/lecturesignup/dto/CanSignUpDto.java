package io.hhp.lecturesignup.dto;

import io.hhp.lecturesignup.entity.UserEntity;

public record CanSignUpDto(
        String message
) {
    public static CanSignUpDto empty(String message) {
        return new CanSignUpDto(message);
    }
}
