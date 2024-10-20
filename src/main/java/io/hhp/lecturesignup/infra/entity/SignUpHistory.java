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
@Table(name = "sign_up_history")
public class SignUpHistory {
    @Id
    @Column(name = "seq", nullable = false)
    private Integer id;

    @ColumnDefault("'-'")
    @Column(name = "user_id", nullable = false, length = 5)
    private String userId;

    @ColumnDefault("'-'")
    @Column(name = "lecture_id", nullable = false, length = 5)
    private String lectureId;

    @ColumnDefault("'-'")
    @Column(name = "regist_date", nullable = false, length = 8)
    private String registDate;

}