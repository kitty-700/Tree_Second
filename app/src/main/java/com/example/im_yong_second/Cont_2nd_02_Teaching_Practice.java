package com.example.im_yong_second;

import static com.example.im_yong_second.SubjectInfo._e;
import static com.example.im_yong_second.SubjectInfo._fb;
import static com.example.im_yong_second.SubjectInfo._ip;
import static com.example.im_yong_second.SubjectInfo._s;

public class Cont_2nd_02_Teaching_Practice {
    static void content_installing(int sc) {//subject code

        SubjectInfo.insert_into_ps(sc, "수업실연");

        _fb(sc, "도입");
        {
            _s();
            _ip("수업준비 확인");
            _ip("선행학습 확인");
            _ip("학습목표 제시");
            _ip("동기유발");
            _e();
        }
        _fb(sc, "전개");
        {
            _s();
            _ip("학습내용 제시");
            _ip("교수(교사)-학습(학생)활동");
            _e();
        }
        _fb(sc, "정리");
        {
            _s();
            _ip("수업 내용의 요약 정리");
            _ip("형성 평가");
            _ip("과제 제시");
            _ip("차시 예고");
            _e();
        }
    }
}

