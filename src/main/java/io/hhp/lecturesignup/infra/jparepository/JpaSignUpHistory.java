package io.hhp.lecturesignup.infra.jparepository;

import io.hhp.lecturesignup.infra.entity.SignUpHistory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaSignUpHistory extends JpaRepository<SignUpHistory, Long> {

    @Query("SELECT COUNT(s.id) FROM SignUpHistory s WHERE s.userId = :userId AND s.lectureId = :lectureId")
    int countSignUpHistoriesByUserIdAndLectureId(@Param("userId") String userId, @Param("lectureId") String lectureId);

    @Modifying
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "INSERT INTO SignUpHistory VALUES (:userId, :lectureId, :registDate)", nativeQuery = true)
    int insertSignUpHistory(@Param("userId") String userId, @Param("lectureId") String lectureId, @Param("registDate") String registDate);

}