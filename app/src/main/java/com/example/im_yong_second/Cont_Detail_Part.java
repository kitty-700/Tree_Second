package com.example.im_yong_second;

public class Cont_Detail_Part {
    /* TODO */
    static final String main_thema = "2차 시험";
    static final int LP = 0; //Local Policy 지역 시책
    static final int IV = 1; //Interview 심층 면접
    static final int TP = 2; //Teaching Practice 수업 실연
    static final int subject_codes[] = {LP,IV,TP};
    static int favorite_subjects[] = {LP,IV,TP};;// Optional.관심 과목 추가. 마땅히 없으면 subject_code[] 와 같은 값을 주면 됨.

    //SB_BST : subject basic theory
    //SB_ANT : subject advanced theory
    //SB_** : subject **

    public static void init_informations() {
        Cont_2nd_00_Local_Policy.content_installing(LP);
        Cont_2nd_01_Interview.content_installing(IV);
        Cont_2nd_02_Teaching_Practice.content_installing(TP);
    }
    /* TODO [END] */
}
