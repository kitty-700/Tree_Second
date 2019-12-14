package com.example.im_yong_second;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    final MainActivity mainActivity = this;
    ListView listView;
    LinearLayout listViewCover;
    TextView navigator;
    Button memo_btn;
    Button piece_insert_btn;
    Button qq_btn;
    EditText piece_input;
    Stack<Piece> pieceStack; //최상위 요소 하나는 있어야하므로 최소 크기가 1임.
    Piece root_piece;
    Piece now_piece;
    TimeEngine timeEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_widget();
        init_listner();
        Initializer.init_static_members();
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //키보드 따라서 화면 올라가도록 하기
        init_members();
        Initializer.contents_initialize(root_piece);
        refresh_display();
    }

    @Override
    protected void onDestroy() {
        kill_timer();
        super.onDestroy();
    }

    void init_widget() {
        this.navigator = (TextView) findViewById(R.id.now_where);
        this.listView = (ListView) findViewById(R.id.item_list);
        this.qq_btn = (Button) findViewById(R.id.qq);
        this.listViewCover = (LinearLayout) findViewById(R.id.item_list_cover);
        this.memo_btn = (Button) findViewById(R.id.display_memo_btn);
        this.piece_input = (EditText) findViewById(R.id.item_input);
        this.piece_insert_btn = (Button) findViewById(R.id.insert_item_btn);
    }

    void init_listner() {
        this.qq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perform_QQ();
            }
        });
        this.qq_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (SubjectInfo.eff_ps.size() == 0) {
                    Toast.makeText(mainActivity, "가능한 퀴즈 없음", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (timeEngine == null) {
                    Toast.makeText(mainActivity, "랜덤 토스트 시작\n" +
                                    Cont_Detail_Part.subject_codes.length + " 中 " + Cont_Detail_Part.favorite_subjects.length + " 개 과목에서\n" +
                                    SubjectInfo.eff_ps.size() + " 개 퀴즈 출제"
                            , Toast.LENGTH_SHORT).show();
                    timeEngine = new TimeEngine(mainActivity);
                } else {
                    Toast.makeText(mainActivity, "랜덤 토스트 끝", Toast.LENGTH_SHORT).show();
                    kill_timer();
                }
                return true;
            }
        });
        this.memo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_have_memo(now_piece))
                    display_memo(now_piece);
            }
        });
        this.listViewCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_have_sub_pieces(now_piece) == false) {
                    onBackPressed();
                }
            }
        });
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Piece picked_piece = now_piece.sub_pieces.get(i);
                if (picked_piece.title.contains(CC.divide_line)) //Leaf면 무시
                    return;
                pieceStack.push(picked_piece);
                now_piece = picked_piece;
                refresh_display();
                //만약 맨 끝단에 내용이 없다면... 심심하니까 메모라도 띄워주자
                if (is_have_sub_pieces(picked_piece) == false) {
                    if (is_have_memo(now_piece)) {
                        display_memo(now_piece);
                    }
                }
            }
        });
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int i, long l) {
                Piece picked_piece = now_piece.sub_pieces.get(i);
                display_memo(picked_piece);
                return true;
            }
        });
        this.piece_insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String now_entered = piece_input.getText().toString().trim();
                switch (now_entered) {
                    //별 의미없는 입력이면 무시한다.
                    case "":
                        return;
                    //특정 명령어를 입력한거면 해당 기능을 수행한다.
                    case "!qq":
                        perform_QQ();
                        break;
                    case "!tr":
                        perform_TR();
                        break;
                    //정상적인 명령이면 Piece를 추가한다.
                    default:
                        now_piece.sub_pieces.add(new Piece(now_entered));
                        break;
                }
                keyboard_kill(piece_input);
                refresh_display();
            }
        });
    }

    void kill_timer() {
        if (timeEngine !=null && timeEngine.timer != null) {
            timeEngine.timer.cancel();
            timeEngine.timer.purge();
            timeEngine.timer = null;
        }
        timeEngine = null;
    }

    void init_members() {
        this.pieceStack = new Stack<Piece>();
        root_piece = new Piece(CC.main_thema);
        pieceStack.push(root_piece);
        now_piece = root_piece;
    }


    void perform_QQ() { //Quiz 로 팝업?
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        int number = (int) (Math.random() * SubjectInfo.eff_ps.size());
        Piece piece = SubjectInfo.eff_ps.get(number);
        builder.setTitle(piece.title);
        builder.setMessage(piece.memo);
        builder.setNegativeButton("OK", null);
        builder.show();
    }

    void perform_TR() { //Tree 형태로 표현 (다른 레이아웃으로 넘겨야할듯?)

    }

    void keyboard_kill(EditText editText) { //입력중이던 내용을 지우고 닫는다.
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(piece_input.getWindowToken(), 0);
        if (editText != null) {
            editText.setText("");
        }
    }

    void display_memo(Piece picked_piece) {
        if (is_have_memo(picked_piece)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(picked_piece.title);
            builder.setMessage(picked_piece.memo);
            builder.setNegativeButton("OK", null);
            builder.show();
        }
    }

    void refresh_display() {    //now_piece를 가지고 화면 재구성
        //목차 갱신
        String string = "";
        for (int i = 0; i < pieceStack.size(); i++) {
            String str = pieceStack.get(i).title;
            if (str.contains("┌ ") || str.contains("│ ") || str.contains("└ "))
                str = str.substring(2);
            string += str + "\n";
            for (int j = 0; j <= i; j++)
                string += "   ";
        }
        navigator.setText(string.trim());

        //리스트 갱신
        ArrayList<String> piece_list = new ArrayList<>();
        ArrayList<Piece> sub_pieces = now_piece.sub_pieces;

        if (sub_pieces.size() != 0) {
            //now_piece의 sub_piece들의 정보를 문자열로 만들어 추가한다.
            for (Piece piece : sub_pieces) {
                String str = piece.title;
                int count = piece.sub_pieces.size();
                if (count != 0)
                    str += " [" + count + " / " + piece.children_counting() + "]";

                if (is_have_memo(piece))
                    str += " ++";
                piece_list.add(str);
            }
        }
        if (is_have_memo(now_piece)) {
            memo_btn.setVisibility(View.VISIBLE);
            memo_btn.setText(now_piece.memo);
        } else {
            memo_btn.setVisibility(View.GONE);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, piece_list);

        listView.setAdapter(adapter);

        listView.setBackgroundColor(Color.parseColor(fill_color()));
    }

    boolean is_have_memo(Piece piece) {
        if (piece.memo != null && piece.memo != "")
            return true;
        return false;
    }

    boolean is_have_sub_pieces(Piece piece) {
        if (piece.sub_pieces.size() != 0)
            return true;
        return false;
    }

    String fill_color() {   //리스트 배경 채우기
        String red_color_code = "FF0000";
        String color = "FF0000";
        int step = 8; //Depth 가 깊어질 때마다 얼마나 배경이 진해질지를 결정
        int max_concentration = 130;
        int s_sz = pieceStack.size(); //stack size
        if (s_sz <= 0 || s_sz >= 16)
            color = "#" + String.format("%02x", 0) + red_color_code;
        else {
            s_sz = s_sz * step;
            if (s_sz >= max_concentration)
                s_sz = max_concentration;
            color = "#" + String.format("%02x", s_sz) + red_color_code;
        }
        return color;
    }

    @Override
    public void onBackPressed() {
        if (pieceStack.size() == 1) {
            display_exit_msg();
        } else {
            //스택을 쌓다보면 스택의 최상단이 now_piece와 같아진다.
            // 이 경우, 뒤로가기를 누르면 방금 전의 piece가 화면에 출력되는데,
            // 이를 보완하기 위해 조건을 따로 두었음.
            if (pieceStack.peek() == now_piece) {
                pieceStack.pop();
                now_piece = pieceStack.peek();
            } else {
                now_piece = pieceStack.pop();
            }
            refresh_display();
        }
    }

    void display_exit_msg() {
        AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
        alert.setPositiveButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActivity.finish();
            }
        });
        alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setMessage("최상위 메뉴입니다.\n종료하시겠습니까?");
        alert.show();
    }
}
