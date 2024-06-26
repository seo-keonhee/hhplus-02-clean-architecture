package io.hhp.lecturesignup.dto;

import io.hhp.lecturesignup.entity.UserEntity;

public record LectureInfoDto(
    String lectureName
    , String startDays
    , String endDays
    , int countSignUp
    , int capacity
) {
    public static LectureInfoDto empty(String lectureName
            , String startDays, String endDays, int countSignUp, int capacity) {
        return new LectureInfoDto(lectureName, startDays, endDays, countSignUp, capacity);
    }
}
