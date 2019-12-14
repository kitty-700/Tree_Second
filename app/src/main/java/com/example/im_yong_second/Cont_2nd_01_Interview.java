package com.example.im_yong_second;

import static com.example.im_yong_second.SubjectInfo._e;
import static com.example.im_yong_second.SubjectInfo._fb;
import static com.example.im_yong_second.SubjectInfo._ip;
import static com.example.im_yong_second.SubjectInfo._s;

public class Cont_2nd_01_Interview {
    static void content_installing(int sc) {//subject code

        SubjectInfo.insert_into_ps(sc, "심층면접");

        _fb(sc, "1");
        {
            _s();
            _ip("2");
            _e();
        }
    }
}
