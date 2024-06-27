package io.hhp.lecturesignup.infra.jparepository;

import io.hhp.lecturesignup.infra.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSchedule extends JpaRepository<Schedule, Long> {
}
