package io.hhp.lecturesignup.domain.service;

import io.hhp.lecturesignup.infra.jparepository.JpaSignUpHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class SignUpHistoryServiceTest {

    @InjectMocks
    private SignUpHistoryService signUpHistoryService;

    @Mock
    private JpaSignUpHistory jpaSignUpHistory;

    @Test
    @DisplayName("신청이력이 존재하는 경우")
    void existSignUpHistoryOk() {
        //given
        String userId = "u0001";
        String lectureId = "l0001";
        //when

        //then
        assertThat(signUpHistoryService.existSignUpHistory(userId,lectureId));
    }

    @Test
    @DisplayName("신청이력이 존재하지않는는 경우")
    void existSignUpHistoryNg() {
        //given
        String userId = "u0010";
        String lectureId = "l0010";
        //when

        //then
        assertThat(!signUpHistoryService.existSignUpHistory(userId,lectureId));

    }

    @Test
    @DisplayName("신청이력 추가가 정상적으로 되는지만 확인")
    void addHistoy() {
        //given
        String userId = "u0010";
        String lectureId = "l0010";
        //when

        //then
        /**
         * 이력 추가 1건이 정상적으로 진행 되었는가 확인
         */
        assertEquals(1, signUpHistoryService.addHistoy(userId,lectureId));
        /**
         *  분명 로직상은 1이 되어야 하는데 실제 테스트는 0이 나오는 이유를 모르겠습니다 ㅠㅠ
         */
    }
}