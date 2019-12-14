package com.example.im_yong_second;

import android.os.Handler;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class TimeEngine { //타임엔진이 하는 역할 : 1초가 지날 때마다 하는 어플리케이션이 할 일을 정의해준다.
    Timer timer;
    MainActivity mainActivity;
    Handler handler = new Handler();
    final int time_delay = 3000;

    TimeEngine(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        timer = new Timer();
        timer.schedule(new RemindTask(),
                0,        //초기 딜레이
                time_delay);  //RemindTask()가 반복적으로 동작할 지연시간

    }//https://blog.naver.com/PostView.nhn?blogId=highkrs&logNo=220283709171

    class RemindTask extends TimerTask {
        public void run() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int number = (int) (Math.random() * SubjectInfo.eff_ps.size());
                    Piece piece = SubjectInfo.eff_ps.get(number);
                    Toast.makeText(mainActivity,
                            piece.memo +
                                    "\n------------------------------------------------------\n" +
                                    "<< " + piece.title + " >>",
                            Toast.LENGTH_LONG).show();
                }
            }, 1000);
        }
    }
}
