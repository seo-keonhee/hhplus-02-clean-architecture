package io.hhp.lecturesignup.entity;

public record UserEntity(
    String userId
    , String userName
) {
    public static UserEntity empty(String userId, String userName) {
        return new UserEntity(userId, userName);
    }
}
