package io.hhp.lecturesignup.entity;

public record HistoryEntity(
        String userId
        , String lectureId
        , String signUpYN
        , long RegistDate
) {
    public static HistoryEntity empty(String userId, String lectureId, String signUpYN) {
        return new HistoryEntity(userId, lectureId, signUpYN ,System.currentTimeMillis());
    }
}
