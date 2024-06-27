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
@Table(name = "schedule")
public class Schedule {
    @Id
    @Column(name = "seq", nullable = false)
    private Integer id;

    @ColumnDefault("'-'")
    @Column(name = "lecture_id", nullable = false, length = 5)
    private String lectureId;

    @ColumnDefault("'-'")
    @Column(name = "days", nullable = false, length = 8)
    private String days;

    @ColumnDefault("'-'")
    @Column(name = "dead_line", nullable = false, length = 8)
    private String deadLine;

    @ColumnDefault("0")
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ColumnDefault("0")
    @Column(name = "po", nullable = false)
    private Integer po;

    public Schedule(Integer id, String lectureId, String days, String deadLine, Integer capacity, Integer po){
        this.id = id;
        this.lectureId = lectureId;
        this.days = days;
        this.deadLine = deadLine;
        this.capacity = capacity;
        this.po = po;
    }

    public Schedule() {

    }
}