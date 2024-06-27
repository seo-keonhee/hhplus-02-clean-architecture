package io.hhp.lecturesignup.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
                    .andExpect(MockMvcResultMatchers.content().string("false"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}