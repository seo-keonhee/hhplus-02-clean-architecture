package io.hhp.lecturesignup.controller;

import io.hhp.lecturesignup.controller.dto.LectureDto;
import io.hhp.lecturesignup.domain.service.LectureService;
import io.hhp.lecturesignup.domain.service.SignUpHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class CommonController {

    private final SignUpHistoryService signUpHistoryService;
    private final LectureService lectureService;

    @Autowired
    public CommonController(SignUpHistoryService signUpHistoryService, LectureService lectureService) {
        this.signUpHistoryService = signUpHistoryService;
        this.lectureService = lectureService;
    }

    //특강 신청 여부 조회
    @GetMapping("/application/{userId}")
    public boolean signUpCheck(@PathVariable(name = "userId") String userId, @RequestParam(name = "lectureId") String lectureId) {
        return signUpHistoryService.existSignUpHistory(userId,lectureId);
    }

    // 특강목록조회(전체 조회)
    // Domain >> DTO 전환 방법은 진우님껄 참고하였습니다.
    // 이런 문법도 존재했군요... 이렇게나 깔끔해지다니...
    @GetMapping
    public ResponseEntity<List<LectureDto>> getAllLectures() {
        return ResponseEntity.ok(lectureService.getLecturesAll().stream().map(LectureDto::toDto).toList());
    }
    // 특강목록조회(특정 강의 조회
    @GetMapping("/{lectureId}")
    public ResponseEntity<List<LectureDto>> getLectureById(@PathVariable(name = "lectureId") String lectureId) {
        return ResponseEntity.ok(lectureService.getLecturesById(lectureId).stream().map(LectureDto::toDto).toList());
    }

    @PostMapping("/apply")
    public boolean applyLecture(@RequestParam(name = "userId") String userId
            , @RequestParam(name = "lectureId") String lectureId
            , @RequestParam(name = "days") String days) {
        // 해당 특강 신청 이력 확인 및 이력 추가
        int signUpYn = signUpHistoryService.addHistoy(userId,lectureId);
        // 해당 특강을 들은적이 없다.
        if(signUpYn == 1) { return lectureService.applyLecture(lectureId,days); }
        // 해당 특강을 다른 날짜 또는 같은 날짜에 신청이력이 있다.
        else { return false; }
    }
}
