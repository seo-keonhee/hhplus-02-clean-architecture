package io.hhp.lecturesignup.service;

import io.hhp.lecturesignup.dto.FindHistoryDto;
import io.hhp.lecturesignup.dto.IdDto;

import java.util.ArrayList;
import java.util.List;

public class FindHIstoryService {
    // 신청이력을 조회하기 위한 서비스
    public List<FindHistoryDto> findHistoryByUserId(IdDto idDto) {
        FindHistoryDto findHistoryDto = FindHistoryDto.empty("","","");
        List<FindHistoryDto> historyDtoList = new ArrayList<>();
        historyDtoList.add(findHistoryDto);
        return historyDtoList;
    }
}
