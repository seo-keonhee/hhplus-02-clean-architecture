package io.hhp.lecturesignup.controller;

import io.hhp.lecturesignup.domain.service.SignUpHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
public class CommonController {

    private final SignUpHistoryService signUpHistoryService;

    @Autowired
    public CommonController(SignUpHistoryService signUpHistoryService) {
        this.signUpHistoryService = signUpHistoryService;
    }

    //특강 신청 여부 조회
    @GetMapping("/application/{userId}")
    public boolean signUpCheck(@PathVariable(name = "userId") String userId, @RequestParam(name = "lectureId") String lectureId) {
        return signUpHistoryService.existSignUpHistory(userId,lectureId);
    }
}
