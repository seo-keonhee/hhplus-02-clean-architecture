package io.hhp.lecturesignup.controller.dto;

import io.hhp.lecturesignup.domain.DomainLecture;

public record LectureDto(
        String lectureId
        , String lectureName
        , String days
        , String deadline
        , int capacity
        , int po
) {
    public static LectureDto toDto(DomainLecture domainLecture){
        return new LectureDto(
                domainLecture.getLectureId()
                , domainLecture.getLectureName()
                , domainLecture.getDays()
                , domainLecture.getDeadLine()
                , domainLecture.getCapacity()
                , domainLecture.getPo());
    }
}
