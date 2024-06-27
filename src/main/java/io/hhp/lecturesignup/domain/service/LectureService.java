package io.hhp.lecturesignup.domain.service;

import io.hhp.lecturesignup.domain.DomainLecture;
import io.hhp.lecturesignup.infra.jparepository.JpaLecture;
import io.hhp.lecturesignup.infra.jparepository.JpaSchedule;
import io.hhp.lecturesignup.infra.mapper.MapperLecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LectureService {

    private final JpaLecture jpaLecture;
    private final JpaSchedule jpaSchedule;

    @Autowired
    public LectureService(final JpaLecture jpaLecture, final JpaSchedule jpaSchedule) {
        this.jpaLecture = jpaLecture;
        this.jpaSchedule = jpaSchedule;
    }

    // 모든 강의목록을 조회힌다.
    public List<DomainLecture> getLecturesAll() {
        MapperLecture mapperLecture = new MapperLecture();
        return mapperLecture.toDomainList(jpaLecture.findLectureALl(), jpaSchedule.findScheduleALl());
    }

    // 지정한 강의의 목록을 조회한다.
    public List<DomainLecture> getLecturesById(String lectureId) {
        MapperLecture mapperLecture = new MapperLecture();
        return mapperLecture.toDomainList(jpaLecture.findLectureByLectureId(lectureId), jpaSchedule.findScheduleByLectureId(lectureId));
    }

    // 신청한다.
    public boolean applyLecture(String lectureId, String days) {
        int addPo = canSignUp(lectureId, days);
        if (addPo == 0) { return false; }
        else { return jpaSchedule.updateSchedule(addPo, days, lectureId) == 1;}
    }

    // 해당 특강현재 수강신청이 가능한지 확인한다.
    private int canSignUp(String lectureId, String days) {
        String nowDate = new SimpleDateFormat("YYYYMMDD").format(new Date());
        String deadLine = jpaSchedule.findScheduleByLectureIdAndDays(lectureId, days).getDeadLine();
        // 신청마감일자이거나 이후라면 신청실패한다.
        if( Integer.parseInt(deadLine) <= Integer.parseInt(nowDate)) { return 0; }

        int po = jpaSchedule.findScheduleByLectureIdAndDays(lectureId, days).getPo();
        int capacity = jpaSchedule.findScheduleByLectureIdAndDays(lectureId, days).getCapacity();
        // 허용인원과 현재인원이 같으면) 신청실패한다.
        if(capacity == po) { return 0; }

        // 신청마감일자보다 이전이고 허용인원보다 현재인원이 적어야 신청가능하다.
        else { return po + 1; }
    }
}
