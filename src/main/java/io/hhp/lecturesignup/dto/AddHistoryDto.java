package io.hhp.lecturesignup.dto;

public record AddHistoryDto(
        String userId
        , String lectureId
        , String signUpYN
) {
    public static AddHistoryDto empty(String userId, String lectureId, String signUpYN) {
        return new AddHistoryDto(userId, lectureId, signUpYN);
    }
}
