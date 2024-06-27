package io.hhp.lecturesignup.infra.jparepository;

import io.hhp.lecturesignup.infra.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLecture extends JpaRepository<Lecture, Long> {
}
