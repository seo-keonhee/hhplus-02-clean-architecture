package io.hhp.lecturesignup.infra.mapper;

import io.hhp.lecturesignup.domain.DomainLecture;
import io.hhp.lecturesignup.infra.entity.Lecture;
import io.hhp.lecturesignup.infra.entity.Schedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public record MapperLecture() {

    // 엔티티(강의)로 매핑
    public Lecture toLectrue(DomainLecture domainLecture) {
        return new Lecture(domainLecture.getLectureId(),domainLecture.getLectureName());
    }

    // 엔티티(강의일정)로 매핑
    public Schedule toSchedule(DomainLecture domainLecture) {
        Schedule schedule = new Schedule();
        schedule.setLectureId(domainLecture.getLectureId());
        schedule.setDays(domainLecture.getDays());
        schedule.setDeadLine(domainLecture.getDeadLine());
        schedule.setCapacity(domainLecture.getCapacity());
        schedule.setPo(domainLecture.getPo());

        return schedule;
    }

    // 도메인 리스트로 매핑(오버로딩: 리스트, 리스트)
    public List<DomainLecture> toDomainList(List<Lecture> lecturesList, List<Schedule> scheduleList) {
        List<DomainLecture> domainLectureList = new ArrayList<>();
        Lecture tempLecture = null;

        for(Schedule schedule : scheduleList) {
            for(Lecture lecture : lecturesList){
                if(lecture.getLectureId().equals(schedule.getLectureId())){
                    tempLecture = lecture;
                }
            }

            domainLectureList.add(toDomain(tempLecture, schedule));
        }
        System.out.println("리스트 : " + domainLectureList);
        return domainLectureList;
    }
    // 도메인 리스트로 매핑(오버로딩: Lecture, 리스트)
    public List<DomainLecture> toDomainList(Lecture lectures, List<Schedule> scheduleList) {
        List<DomainLecture> domainLectureList = new ArrayList<>();
        Lecture tempLecture = null;

        for(Schedule schedule : scheduleList) {

            domainLectureList.add(toDomain(lectures, schedule));
        }
        return domainLectureList;
    }

    // 도메인으로 매핑
    private DomainLecture toDomain(Lecture lecture, Schedule schedule) {
        DomainLecture domainLecture = new DomainLecture();
        domainLecture.setLectureId(lecture.getLectureId());
        domainLecture.setLectureName(lecture.getLectureName());
        domainLecture.setDays(schedule.getDays());
        domainLecture.setDeadLine(schedule.getDeadLine());
        domainLecture.setCapacity(schedule.getCapacity());
        domainLecture.setPo(schedule.getPo());

        return domainLecture;
    }
}
