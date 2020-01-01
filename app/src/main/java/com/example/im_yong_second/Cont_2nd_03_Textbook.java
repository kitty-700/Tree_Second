package com.example.im_yong_second;

import static com.example.im_yong_second.SubjectInfo._e;
import static com.example.im_yong_second.SubjectInfo._fb;
import static com.example.im_yong_second.SubjectInfo._ip;
import static com.example.im_yong_second.SubjectInfo._s;

public class Cont_2nd_03_Textbook {
    static void content_installing(int sc) {//subject code

        SubjectInfo.insert_into_ps(sc, "교과서");

        _fb(sc, "중학교");
        {
            _s();
            _ip("정보문화");
            {
                _s();
                _ip("정보 사회");
                _ip("정보 윤리");
                _e();
            }
            _ip("자료와 정보");
            {
                _s();
                _ip("자료와 정보의 표현");
                _ip("자료와 정보의 분석");
                _e();
            }
            _ip("문제 해결과 프로그래밍");
            {
                _s();
                _ip("추상화와 알고리즘");
                _ip("프로그래밍");
                _e();
            }
            _ip("컴퓨팅 시스템");
            {
                _s();
                _ip("컴퓨팅 시스템의 구성과 작동");
                _ip("피지컬 컴퓨팅");
                _e();
            }
            _e();
        }
        _fb(sc, "고등학교");
        {
            _s();
            _ip("정보문화");
            {
                _s();
                _ip("정보 사회");
                _ip("정보 윤리");
                _e();
            }
            _ip("자료와 정보");
            {
                _s();
                _ip("자료와 정보의 표현");
                _ip("자료와 정보의 분석");
                _e();
            }
            _ip("문제 해결과 프로그래밍");
            {
                _s();
                _ip("추상화와 알고리즘");
                _ip("프로그래밍");
                _e();
            }
            _ip("컴퓨팅 시스템");
            {
                _s();
                _ip("컴퓨팅 시스템의 구성과 작동");
                _ip("피지컬 컴퓨팅");
                _e();
            }
            _e();
        }
    }
}
