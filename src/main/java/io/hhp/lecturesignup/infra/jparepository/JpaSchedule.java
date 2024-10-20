package io.hhp.lecturesignup.infra.jparepository;

import io.hhp.lecturesignup.infra.entity.Lecture;
import io.hhp.lecturesignup.infra.entity.Schedule;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaSchedule extends JpaRepository<Schedule, Long> {

    @Query("SELECT new Schedule(s.id, s.lectureId, s.days, s.deadLine, s.capacity, s.po) FROM Schedule s")
    List<Schedule> findScheduleALl();

    @Query("SELECT new Schedule(s.id, s.lectureId, s.days, s.deadLine, s.capacity, s.po) FROM Schedule s WHERE s.lectureId = :lectureId")
    List<Schedule> findScheduleByLectureId(@Param("lectureId") String lectureId);

    /** 수강신청을 할 수 있는지 판단로직에 사용되는 쿼리입니다.
     * 이곳에 락을 걸어서 데이터를 인서트 하기전 한명씩 수강신청 가능여부를 비교할 수 있습니다.
     * "조회(여기서 비관적 락으로 동시성 제어) >> 비교 >> 갱신 및 이력 추가"
     * 위의 순차가 틀렸다면 피드백 부탁드립니다.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT new Schedule(s.id, s.lectureId, s.days, s.deadLine, s.capacity, s.po) FROM Schedule s WHERE s.lectureId = :lectureId AND s.days = :days")
    Schedule findScheduleByLectureIdAndDays(@Param("lectureId") String lectureId, @Param("days") String days);

    @Modifying
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "UPDATE Schedule SET po = :po WHERE lectureId = :lectureId AND days = :days", nativeQuery = true)
    int updateSchedule(@Param("po") int po, @Param("days") String days, @Param("lectureId") String lectureId);
}
