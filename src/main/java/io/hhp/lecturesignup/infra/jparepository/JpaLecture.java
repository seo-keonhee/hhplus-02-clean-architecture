package io.hhp.lecturesignup.infra.jparepository;

import io.hhp.lecturesignup.infra.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLecture extends JpaRepository<Lecture, Long> {
    @Query("SELECT new Lecture(l.lectureId, l.lectureName) FROM Lecture l WHERE l.lectureId = :lectureId")
    Lecture findLectureByLectureId(@Param("lectureId") String lectureId);

    @Query("SELECT new Lecture(l.lectureId, l.lectureName) FROM Lecture l")
    List<Lecture> findLectureALl();
}
