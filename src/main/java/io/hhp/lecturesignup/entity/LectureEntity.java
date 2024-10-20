package io.hhp.lecturesignup.entity;

public record LectureEntity(
    String lectureId
    , String lectureName
    , String startDays
    , String endDays
    , int capacity
) {
    public static LectureEntity empty(String lectureId, String lectureName, int capacity) {
        return new LectureEntity(lectureId, lectureName, "20240626", "20240630",capacity);
    }
}
