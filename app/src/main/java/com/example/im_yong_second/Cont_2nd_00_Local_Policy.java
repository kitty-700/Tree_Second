package com.example.im_yong_second;

import static com.example.im_yong_second.SubjectInfo.*;

public class Cont_2nd_00_Local_Policy {
    static void content_installing(int sc) {//subject code

        SubjectInfo.insert_into_ps(sc, "시책");

        _fb(sc, "기초가 강한 교육");
        {
            _s();
            _ip("탁월성 교육","");
            _ip("보편적 학습설계","");
            _e();
        }
        _fb(sc, "미래를 여는 교실");
        {
            _s();
            _ip("미래지향적 학력","기본적인 학력에 ㄷ더해 존엄한 인간으로 살아갈 수 있는 역량\n+\n더 좋은 사회를 만들어나갈 민주시민으로서 성장할 수 있는 역량");
            _e();
        }
        _fb(sc, "건강하고 안전한 학교");
        {
            _s();
            _e();
        }
        _fb(sc, "모두에게 따듯한 교육복지");
        {
            _s();
            _ip("최소극대화","더 어려운 곳에 더 많은 지원을 하는 것");
            _ip("돈 안드는 교육","'태어난 곳은 달라도 배움의 기회는 같아야한다.' 는 교육 공공성의 철학을 실질적으로 구현");
            _e();
        }
        _fb(sc, "사람을 위한 교육행정");
        {
            _s();
            _e();
        }
    }
}
