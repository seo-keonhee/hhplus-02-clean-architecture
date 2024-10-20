package io.hhp.lecturesignup.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainLecture {
    private String lectureId;
    private String lectureName;
    private String days;
    private String deadLine;
    private Integer capacity;
    private Integer po;
}
