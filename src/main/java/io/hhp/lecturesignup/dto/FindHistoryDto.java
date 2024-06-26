package io.hhp.lecturesignup.dto;

public record FindHistoryDto(
    String userName
    , String lectureName
    , String isSignUp
) {
    public static FindHistoryDto empty(String userName, String lectureName, String isSignUp) {
        return new FindHistoryDto(userName, lectureName, isSignUp);
    }
}
