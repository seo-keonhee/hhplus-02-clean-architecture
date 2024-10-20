package io.hhp.lecturesignup.domain.service;

import io.hhp.lecturesignup.infra.jparepository.JpaSignUpHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SignUpHistoryService {

    private final JpaSignUpHistory jpaSignUpHistory;

    @Autowired
    public SignUpHistoryService(final JpaSignUpHistory jpaSignUpHistory) {
        this.jpaSignUpHistory = jpaSignUpHistory;
    }

    // 수강이력을 조회한다.
    public boolean existSignUpHistory(String userId, String lectureId) {
        return jpaSignUpHistory.countSignUpHistoriesByUserIdAndLectureId(userId,lectureId) > 0;
    }

    // 수강이력을 추가한다.
    public int addHistoy(String userId, String lectureId) {
        String nowDate = new SimpleDateFormat("YYYYMMDD").format(new Date());
        return jpaSignUpHistory.insertSignUpHistory(userId, lectureId, nowDate);
    }
}
