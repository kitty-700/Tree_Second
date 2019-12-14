package com.example.im_yong_second;

import java.util.ArrayList;

public class Initializer {
    static void init_static_members() {
        SubjectInfo.pi = 0;
        SubjectInfo.mainActivity = null;
        SubjectInfo.ps = new ArrayList<>();
        SubjectInfo.eff_ps = new ArrayList<>();
        SubjectInfo.pp = new Piece[20];
        SubjectInfo.now_installing_subject_is = -1; //이후 _fb()에서 갱신됨
        /* DP 이용, 선호하는 과목을 SubjectInfo 클래스의 favorite_subjects_bool[] 에 표시 */
        SubjectInfo.favorite_subjects_bool = new boolean[Cont_Detail_Part.subject_codes.length];
        for (int i = 0; i < Cont_Detail_Part.subject_codes.length; i++) {
            SubjectInfo.favorite_subjects_bool[i] = false;
        }
        for (int i = 0; i < Cont_Detail_Part.favorite_subjects.length; i++) {
            SubjectInfo.favorite_subjects_bool[Cont_Detail_Part.favorite_subjects[i]] = true;
        }
        /* DP 이용, 선호하는 과목 표시 [END] */
        Piece.ID_next = 0;
    }

    static public void contents_initialize(Piece root_piece) {
        SubjectInfo.init_informations();
        for (Piece piece : SubjectInfo.ps) {
            root_piece.put(piece);
        }
    }
}
