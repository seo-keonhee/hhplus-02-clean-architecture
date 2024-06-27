package io.hhp.lecturesignup.infra.jparepository;

import io.hhp.lecturesignup.infra.entity.SignUpHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaSignUpHistory extends JpaRepository<SignUpHistory, Long> {

    @Query("SELECT COUNT(s.id) FROM SignUpHistory s WHERE s.userId = :userId AND s.lectureId = :lectureId")
    int countSignUpHistoriesByUserIdAndLectureId(@Param("userId") String userId, @Param("lectureId") String lectureId);
}