package com.example.im_yong_second;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class SubjectInfo {
    static ArrayList<Piece> ps; //pieces
    static Piece pp[]; //Piece Pointer. 스택과 연계될 최대 레벨 20 이지만 여기까지 누가 쓸까?
    static int pi;    //Piece pointer Index
    static MainActivity mainActivity;
    static ArrayList<Piece> eff_ps;//effective pieces
    static boolean favorite_subjects_bool[];//effective pieces
    static int now_installing_subject_is;

    //////////////////final members
/*
    int sc = <SUB>; //subject code

    _fb(sc, "타이틀0");
    {
        _s();
        _ip("타이틀2");
        {
            _s();
            _ip("타이틀3");
            _e();
        }
        _e();
    } //-> fb 전까지는 _e(); 해줘야한다.
*/
    //(start) _fb() overloading/////////////////////////////////////////////
    public static void _fb(int subject_code, String title, Boolean al, Integer itn, String memo) { //first branch
        if (pi != 0) { //여기서 subject_code는 대충 어디서 에러가 발생하는지 가늠하기 위해 사용됨
            Log.e("kitty", subject_code + " error in " + title);
            if (mainActivity != null)
                Toast.makeText(mainActivity, "데이터 파일에 에러가 있는듯\n" + subject_code + "-" + title, Toast.LENGTH_LONG).show();
        }
        SubjectInfo.now_installing_subject_is = subject_code;
        pi = 0;
        ps.get(subject_code).put(pp[pi] = new Piece(title, al, itn, memo));
    }

    public static void _fb(int subject_code, String title) {
        _fb(subject_code, title, null, null, null);
    }

    public static void _fb(int subject_code, String title, Boolean al) {
        _fb(subject_code, title, al, null, null);
    }

    public static void _fb(int subject_code, String title, Integer itn) {
        _fb(subject_code, title, null, itn, null);
    }

    public static void _fb(int subject_code, String title, String memo) {
        _fb(subject_code, title, null, null, memo);
    }

    public static void _fb(int subject_code, String title, Boolean al, String memo) {
        _fb(subject_code, title, al, null, memo);
    }

    public static void _fb(int subject_code, String title, Integer itn, String memo) {
        _fb(subject_code, title, null, itn, memo);
    }
    //(end) _fb() overloading/////////////////////////////////////////////

    //(start) _ip() overloading/////////////////////////////////////////////
    public static void _ip(String title, Boolean al, Integer itn, String memo) {//insert piece 목차 항목 넣기
        pp[pi - 1].put(pp[pi] = new Piece(title, al, itn, memo)); //부모 (pp[pi-1]) 에 new_piece를 자식 (pp[pi])으로서 넣는다.
    }

    public static void _ip(String title) {
        _ip(title, null, null, null);
    }

    public static void _ip(String title, String memo) {
        _ip(title, null, null, memo);
    }

    public static void _ip(String title, Integer itn) {
        _ip(title, null, itn, null);
    }

    public static void _ip(String title, Boolean al) {
        _ip(title, al, null, null);
    }

    public static void _ip(String title, Boolean al, Integer itn) {
        _ip(title, al, itn, null);
    }

    public static void _ip(String title, Boolean al, String memo) {
        _ip(title, al, null, memo);
    }

    public static void _ip(String title, Integer itn, String memo) {
        _ip(title, null, itn, memo);
    }

    public static void _ip(Piece init_target_piece) { //globalized 된 Piece 를 가져오는 오버로딩함수
        pp[pi - 1].put(pp[pi] = init_target_piece);
    }
    //(end) _ip() overloading/////////////////////////////////////////////

    public static void _s() {//start. 목차의 시작
        pi++;
    }

    public static void _e() {//end. 목차의 끝
        pi--;
    }

    ///////////////////
    public static Piece _get_above() { //get above
        return pp[pi - 1]; //_s() 가 선행되므로 이전 Piece를 지칭하기 위해 pp[pi-1]
    }

    public static void insert_into_ps(int subject_code, String subject_title) {
        insert_into_ps(subject_code, subject_title, null);
    }

    public static void insert_into_ps(int subject_code, String subject_title, String memo) {
        SubjectInfo.now_installing_subject_is = subject_code;
        SubjectInfo.ps.add(new Piece(subject_title, true, 8, memo));
    }
    ///////////////////
    public static void init_informations() {
        Cont_Detail_Part.init_informations();
    }
}
