package com.example.im_yong_second;

public class Cont_Detail_Part {
    /* TODO */
    static final String main_thema = "2차 시험";
    static final int LP = 0; //Local Policy 지역 시책
    static final int subject_codes[] = {LP};
    static int favorite_subjects[] = {LP};;// Optional.관심 과목 추가. 마땅히 없으면 subject_code[] 와 같은 값을 주면 됨.

    //SB_BST : subject basic theory
    //SB_ANT : subject advanced theory
    //SB_** : subject **

    public static void init_informations() {
        Cont_2nd_00_Local_Policy.content_installing(LP);
    }
    /* TODO [END] */
}
