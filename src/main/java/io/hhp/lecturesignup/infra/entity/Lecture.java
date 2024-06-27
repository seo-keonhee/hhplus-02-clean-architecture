package io.hhp.lecturesignup.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @ColumnDefault("'-'")
    @Column(name = "lecture_id", nullable = false, length = 5)
    private String lectureId;

    @ColumnDefault("'-'")
    @Column(name = "lecture_name", nullable = false, length = 50)
    private String lectureName;

    public Lecture(String lectureId, String lectureName) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
    }

    public Lecture() {

    }
}