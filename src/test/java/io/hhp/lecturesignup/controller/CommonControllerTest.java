package io.hhp.lecturesignup.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.ExecutorService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CommonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("신청이력이 존재하는 경우 확인")
    void signUpCheckTrue() {
        //given
        String userId = "u0001";
        String lectureId = "l0001";

        //when

        //then
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/lectures/application/" + userId)
                            .param("lectureId", lectureId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.content().string("true"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("신청이력이 존재하지 않는 경우 확인")
    void signUpCheckFalse() {
        //given
        String userId = "u0010";
        String lectureId = "l0001";

        //when
        //then
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/lectures/application/" + userId)
                            .param("lectureId", lectureId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.content().string("false"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("강의목록전체조회")
    void getLecture() {
        //given
        // 전체 목록조회이므로 조건 없음
        //when
        //then
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/lectures"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("강의목록일부조회")
    void getLectureById() {
        //given
        String lectureId = "l0001";

        //when
        //then
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/lectures/" + lectureId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 실제 DB에 테스트 데이터 입력하여 확인
     * 강의아이디 lectureId : l0001
     * 강일일정 days : 20240701
     * 마감기한 deadline: 20240630
     * 허용인원 capacity : 30
     * 현재인원 po : 0
     */
    @Test
    @DisplayName("신청이력이 존재하지 않고 인원의 여유도 있는 경우 추가 확인")
    void applyLectureOk() throws Exception {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        String lectureId = "l0001";

        //when
        for (int i = 1; i <= 30; i++) {
            String userID = "u" + i;
            executorService.submit(() -> {
                try {
                    mockMvc.perform(MockMvcRequestBuilders.get("/lectures/apply")
                                    .param("userId", userID)
                                    .param("lectureId", lectureId)
                                    .param("days", "20240701"))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(print())
                            .andExpect(MockMvcResultMatchers.content().string("ture"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        //then
    }

    /**
     * 실제 DB에 테스트 데이터 입력하여 확인
     * 강의아이디 lectureId : l0002
     * 강일일정 days : 20240703
     * 마감기한 deadline: 20240630
     * 허용인원 capacity : 10
     * 현재인원 po : 10
     */
    @Test
    @DisplayName("신청이력이 존재하지 않으나 인원의 여유가 없는 경우 추가 확인")
    void applyLectureNg1() throws Exception {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        String lectureId = "l0002";

        //when
        for (int i = 1; i <= 30; i++) {
            String userID = "u" + i;
            executorService.submit(() -> {
                try {
                    mockMvc.perform(MockMvcRequestBuilders.get("/lectures/apply")
                                    .param("userId", userID)
                                    .param("lectureId", lectureId)
                                    .param("days", "20240703"))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(print())
                            .andExpect(MockMvcResultMatchers.content().string("false"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        //then
    }

    /**
     * 실제 DB에 테스트 데이터 입력하여 확인
     * 강의아이디 lectureId : l0001
     * 강일일정 days : 20240701
     * 마감기한 deadline: 20240630
     * 허용인원 capacity : 30
     * 현재인원 po : 1
     * 유저아이디 userId : u0001
     */
    @Test
    @DisplayName("신청이력이 존재하여 인원의 여유가 있어도 실패하는 경우 추가 확인")
    void applyLectureNg2() throws Exception {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        String userID = "u0001";
        String lectureId = "l0001";

        //when
        for (int i = 1; i <= 30; i++) {

            executorService.submit(() -> {
                try {
                    mockMvc.perform(MockMvcRequestBuilders.get("/lectures/apply")
                                    .param("userId", userID)
                                    .param("lectureId", lectureId)
                                    .param("days", "20240701"))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(print())
                            .andExpect(MockMvcResultMatchers.content().string("false"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        //then
    }
}