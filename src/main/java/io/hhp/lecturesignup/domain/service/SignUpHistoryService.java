package io.hhp.lecturesignup.domain.service;

import io.hhp.lecturesignup.infra.jparepository.JpaSignUpHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpHistoryService {

    private final JpaSignUpHistory jpaSignUpHistory;

    @Autowired
    public SignUpHistoryService(final JpaSignUpHistory jpaSignUpHistory) {
        this.jpaSignUpHistory = jpaSignUpHistory;
    }

    public boolean existSignUpHistory(String userId, String lectureId) {
        return jpaSignUpHistory.countSignUpHistoriesByUserIdAndLectureId(userId,lectureId) > 0;
    }
}
