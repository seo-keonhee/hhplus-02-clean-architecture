package io.hhp.lecturesignup.service;

import io.hhp.lecturesignup.dto.CanSignUpDto;
import io.hhp.lecturesignup.dto.IdDto;
import io.hhp.lecturesignup.dto.LectureInfoDto;

public class FindLectureService {
    // 강의정보를 조회하기 위한 서비스
    public LectureInfoDto findLectureInfo(IdDto idDto) {
        return LectureInfoDto.empty("","","",0,0);
    }
    // 특강신청이 가능한지 확인하는 서비스
    public CanSignUpDto canSignup(IdDto idDto) {
        return CanSignUpDto.empty("");
    }
    // 특강의 수강신청에 성공한 사람들의 수를 확인하는 서비스
    private int registerCountSignUp(String lectureId){
        return 0;
    }
}
