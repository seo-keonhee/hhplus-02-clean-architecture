package io.hhp.lecturesignup.dto;

public record IdDto(
    String userId
    , String lectureid
) {
    public static IdDto empty(String userId, String lectureid) {
        return new IdDto(userId, lectureid);
    }
}
