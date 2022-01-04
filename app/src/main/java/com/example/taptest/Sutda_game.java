package com.example.taptest;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sutda_game extends Fragment implements View.OnClickListener {

    View v;
    Dialog mDialog, rDialog;

    // n월일 때 일반패 2n-2 , 광 및 열끗 2n-1
    int[] paelist = {R.drawable.hong_1, R.drawable.gwang_1, R.drawable.mae_2, R.drawable.bird_2, R.drawable.hong_3, R.drawable.gwang_3,
    R.drawable.hong_4, R.drawable.bird_4, R.drawable.flower_5, R.drawable.hong_5, R.drawable.flower_6, R.drawable.chung_6, R.drawable.hong_7, R.drawable.pig_7,
    R.drawable.bird_8, R.drawable.gwang_8, R.drawable.guk_9, R.drawable.chung_9, R.drawable.chung_10, R.drawable.deer_10};

    String[] jokbo = {"망통", "한끗", "두끗", "세끗", "네끗", "다섯끗", "여섯끗", "일곱끗", "여덟끗", "갑오", "세륙", "장사", "장삥", "구삥", "독사", "알리", "1땡", "2땡",
    "3땡", "4땡", "5땡", "6땡", "7땡", "8땡", "9땡", "장땡", "13광땡", "18광땡", "38광땡"};

    String[] jokbo2 = {"암행어사", "땡잡이", "멍텅구리 구사", "구사"};

    private int pst[];
    private long lastCallAmount;
    private boolean callState;

    private TextView jokbo_Com, jokbo_User;
    private String jokCom, jokUser;

    private TextView money_Com, money_User;
    private long commoney, usermoney;

    private TextView pandon;
    private long money;

    private ImageView compae1, compae2, userpae1, userpae2;

    public Sutda_game() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.sutda, container, false);

        jokbo_Com = v.findViewById(R.id.jokbo_com);
        jokbo_User = v.findViewById(R.id.textView3);
        money_Com = v.findViewById(R.id.Money);
        money_User = v.findViewById(R.id.money);
        pandon = v.findViewById(R.id.pandone2);

        compae1 = v.findViewById(R.id.com1);
        compae2 = v.findViewById(R.id.user1);
        userpae1 = v.findViewById(R.id.user2);
        userpae2 = v.findViewById(R.id.pae);

        initGame();

        Button jokbo = v.findViewById(R.id.jokbo_dialog);
        Button paedol = v.findViewById(R.id.jokbo_dialog3);
        Button bat = v.findViewById(R.id.jokbo_dialog2);
        Button reset = v.findViewById(R.id.jokbo_dialog4);

        jokbo.setOnClickListener(this);
        paedol.setOnClickListener(this);
        bat.setOnClickListener(this);
        reset.setOnClickListener(this);

        mDialog = new Dialog(v.getContext());
        mDialog.setContentView(R.layout.dialog_jokbo);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        rDialog = new Dialog(v.getContext());
        rDialog.setContentView(R.layout.bet_dialog);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.jokbo_dialog: {
                mDialog.show();
                break;
            }
            case R.id.jokbo_dialog2: {
                if(money == 0) {
                    Toast.makeText(getContext(), "배팅 전에 패를 돌려주세요", Toast.LENGTH_SHORT).show();
                } else {
                    TextView bettingHalf = rDialog.findViewById(R.id.textView12);
                    TextView bettingCall = rDialog.findViewById(R.id.textView10);
                    bettingHalf.setText(convert(money/2));
                    bettingCall.setText(convert(lastCallAmount));

                    rDialog.show();
                    Button die = rDialog.findViewById(R.id.button4);
                    Button call = rDialog.findViewById(R.id.button5);
                    Button half = rDialog.findViewById(R.id.button7);
                    die.setOnClickListener(this);
                    call.setOnClickListener(this);
                    half.setOnClickListener(this);
                }
                break;
            }
            case R.id.jokbo_dialog3: {
                if(money == 0) {
                    Toast.makeText(getContext(), "패를 돌립니다", Toast.LENGTH_SHORT).show();
                    paedol();
                    Toast.makeText(getContext(), "먼저 배팅해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "진행중에는 패를 돌릴 수 없습니다", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button4: {
                Toast.makeText(getContext(), "죽었습니다!", Toast.LENGTH_SHORT).show();
                openpae();
                diecal();
                Toast.makeText(getContext(), "다시 패를 돌려주세요", Toast.LENGTH_SHORT).show();
                rDialog.dismiss();
                break;
            }
            case R.id.button7: {
                if(callState) {
                    Toast.makeText(getContext(), "콜 혹은 다이만 가능합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    betcal(money/2);
                    rDialog.dismiss();
                    Toast.makeText(getContext(), "하프", Toast.LENGTH_SHORT).show();
                    aibet();
                }
                break;
            }
            case R.id.jokbo_dialog4: {
                Toast.makeText(getContext(), "판을 엎었습니다!", Toast.LENGTH_SHORT).show();
                initGame();
                Toast.makeText(getContext(), "다시 패를 돌려주세요", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.button5: {
                if(lastCallAmount == 0) {
                    Toast.makeText(getContext(), "바로 콜 할 수 없습니다", Toast.LENGTH_SHORT).show();
                } else {
                    if(callState) {
                        betcal(lastCallAmount);
                        rDialog.dismiss();
                        Toast.makeText(getContext(), "패를 깝니다.", Toast.LENGTH_SHORT).show();
                        bothCallState(pst);

                    } else {
                        betcal(lastCallAmount);
                        callState = true;
                        rDialog.dismiss();
                        Toast.makeText(getContext(),"콜", Toast.LENGTH_SHORT).show();
                        aibet();
                    }
                }
                break;
            }
        }
    }

    public void bothCallState(int[] pst) { // 둘 다 모두 call 에 동의함. 패를 오픈하고 승패 결정 후 새 게임 시작. 승 패 혹은 재경기 가능.
        openpae();
        String comjok = getjokbo(pst[0], pst[1]);
        String userjok = getjokbo(pst[2], pst[3]);
        int state = winner(comjok, userjok);
        if(state == 2) {
            Toast.makeText(getContext(), "이겼습니다!", Toast.LENGTH_SHORT).show();
            diecom();
            if(commoney == 0) {
                Toast.makeText(getContext(), "고니가 승리했습니다", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getContext(), "다시 패를 돌려주세요", Toast.LENGTH_SHORT).show();
        } else if (state == 1){
            Toast.makeText(getContext(), "졌습니다ㅜㅜ", Toast.LENGTH_SHORT).show();
            diecal();
            if(usermoney == 0) {
                Toast.makeText(getContext(), "정마담이 승리했습니다", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getContext(), "다시 패를 돌려주세요", Toast.LENGTH_SHORT).show();
        } else {
            // 비김
            Toast.makeText(getContext(), "비겼습니다. 재경기 진행합니다.", Toast.LENGTH_SHORT).show();
            commoney += money/2;
            usermoney += money/2;
            money = 0;
            lastCallAmount = 0;
            callState = false;
            pandon.setText(convert(money));
            money_Com.setText(convert(commoney));
            pandon.setText(convert(money));
            money_User.setText(convert(usermoney));
            Toast.makeText(getContext(), "패를 돌려주세요", Toast.LENGTH_SHORT).show();
        }
    }

    public int winner(String p1, String p2) {
        if(Arrays.asList(jokbo2).contains(p1) && Arrays.asList(jokbo2).contains(p2)) {
            switch(p1) {
                case "암행어사": {
                    if(p2 == "땡잡이") {
                        return 1;
                    } else {
                        return 0;
                    }
                }
                case "땡잡이": {
                    if(p2 == "암행어사") {
                        return 2;
                    } else {
                        return 0;
                    }
                }
                case "멍텅구리 구사":
                case "구사": {
                    return 0;
                }
            }

        } else if(Arrays.asList(jokbo2).contains(p1)) {
            int idx = Arrays.asList(jokbo).indexOf(p2);
            switch(p1) {
                case "암행어사": {
                    if(p2 == "13광땡" || p2 == "18광땡") {
                        return 1;
                    } else {
                        if(idx > 1) {
                            return 2;
                        } else if (idx < 1) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                case "땡잡이": {
                    if(idx >= 16 && idx <= 24) {
                        return 1;
                    } else {
                        if(idx > 0) {
                            return 2;
                        } else {
                            return 0;
                        }
                    }
                }
                case "멍텅구리 구사": {
                    if(idx <= 24) {
                        Toast.makeText(getContext(), "멍텅구리 구사 재경기", Toast.LENGTH_SHORT).show();
                        return 0;
                    } else {
                        return 2;
                    }
                }
                case "구사": {
                    if(idx <= 15) {
                        Toast.makeText(getContext(), "구사 재경기", Toast.LENGTH_SHORT).show();
                        return 0;
                    } else {
                        return 2;
                    }
                }
            }
        } else if(Arrays.asList(jokbo2).contains(p2)) {
            int idx = Arrays.asList(jokbo).indexOf(p1);
            switch(p2) {
                case "암행어사": {
                    if(p1 == "13광땡" || p1 == "18광땡") {
                        return 2;
                    } else {
                        if(idx > 1) {
                            return 1;
                        } else if (idx < 1) {
                            return 2;
                        } else {
                            return 0;
                        }
                    }
                }
                case "땡잡이": {
                    if(idx >= 16 && idx <= 24) {
                        return 2;
                    } else {
                        if(idx > 0) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                case "멍텅구리 구사": {
                    if(idx <= 24) {
                        Toast.makeText(getContext(), "멍텅구리 구사 재경기", Toast.LENGTH_SHORT).show();
                        return 0;
                    } else {
                        return 1;
                    }
                }
                case "구사": {
                    if(idx <= 15) {
                        Toast.makeText(getContext(), "구사 재경기", Toast.LENGTH_SHORT).show();
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        } else {
            int idx1 = Arrays.asList(jokbo).indexOf(p1);
            int idx2 = Arrays.asList(jokbo).indexOf(p2);
            if(idx1 > idx2) {
                return 1;
            } else if(idx1 < idx2) {
                return 2;
            } else {
                return 0;
            }
        }
        return -1;
    }

    public void aibet() {
        // 콤퓨터는 random bet
        Random rand = new Random();
        int val = rand.nextInt(3); //2 half 1 call 0 die
        if (val == 2) {
            if(callState) {
                aibet();
            } else {
                combetcal(money/2);
                Toast.makeText(getContext(), "정마담 : 하프", Toast.LENGTH_SHORT).show();
            }
        } else if(val == 1) {
            if(lastCallAmount == 0) {
                aibet();
            } else {
                if(callState) {
                    Toast.makeText(getContext(), "정마담 : 콜", Toast.LENGTH_SHORT).show();
                    combetcal(lastCallAmount);
                    Toast.makeText(getContext(), "패를 깝니다.", Toast.LENGTH_SHORT).show();
                    bothCallState(pst);

                } else {
                    combetcal(lastCallAmount);
                    callState = true;
                    Toast.makeText(getContext(), "정마담 : 콜", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            diecom();
            Toast.makeText(getContext(),"정마담이 죽었습니다", Toast.LENGTH_SHORT).show();
            openpae();
            Toast.makeText(getContext(), "패를 다시 돌려주세요", Toast.LENGTH_SHORT).show();
        }
    }

    public void betcal(long m) {
        if(usermoney <= m) {
            Toast.makeText(getContext(), "올인했습니다", Toast.LENGTH_SHORT).show();
            betcal(usermoney);
            callState = true;
        } else {
            money += m;
            usermoney -= m;
            lastCallAmount = m;
            pandon.setText(convert(money));
            money_User.setText(convert(usermoney));
        }
    }

    public void diecal() {
        commoney += money;
        money = 0;
        lastCallAmount = 0;
        callState = false;
        pandon.setText(convert(money));
        money_Com.setText(convert(commoney));
    }

    public void combetcal(long m) {
        if(commoney <= m) {
            Toast.makeText(getContext(), "정마담이 올인했습니다", Toast.LENGTH_SHORT).show();
            combetcal(commoney);
            callState = true;
        } else {
            money += m;
            commoney -= m;
            lastCallAmount = m;
            pandon.setText(convert(money));
            money_Com.setText(convert(commoney));
        }
    }

    public void diecom() {
        usermoney += money;
        money = 0;
        lastCallAmount = 0;
        callState = false;
        pandon.setText(convert(money));
        money_User.setText(convert(usermoney));
    }

    public void initGame() {
        jokCom = "족보";
        jokUser = "족보";
        commoney = 100000000;
        usermoney = 100000000;
        money = 0;
        lastCallAmount = 0;
        callState = false;

        compae1.setImageResource(R.drawable.tu_back);
        compae2.setImageResource(R.drawable.tu_back);
        userpae1.setImageResource(R.drawable.tu_back);
        userpae2.setImageResource(R.drawable.tu_back);
        jokbo_Com.setText(jokCom);
        jokbo_User.setText(jokUser);
        money_Com.setText(convert(commoney));
        money_User.setText(convert(usermoney));
        pandon.setText(convert(money));
    }

    public void paedol() {
        if(commoney < 500000 || usermoney < 500000) {
            Toast.makeText(getContext(), "돈이 없어요 재경기 할게요..", Toast.LENGTH_SHORT).show();
            initGame();
        } else {
            // 50 씩 빼서 판돈 만들기
            commoney -= 500000;
            usermoney -= 500000;
            money += 1000000;

            money_Com.setText(convert(commoney));
            money_User.setText(convert(usermoney));
            pandon.setText(convert(money));

            pst = randint();
            compae1.setImageResource(R.drawable.tu_back);
            compae2.setImageResource(R.drawable.tu_back);
            userpae1.setImageResource(paelist[pst[2]]);
            userpae2.setImageResource(paelist[pst[3]]);

            String userjok = getjokbo(pst[2], pst[3]);
            jokbo_User.setText(userjok);
            jokbo_Com.setText("???");
        }
    }

    public void openpae() {
        compae1.setImageResource(paelist[pst[0]]);
        compae2.setImageResource(paelist[pst[1]]);
        String comjok = getjokbo(pst[0], pst[1]);
        Toast.makeText(getContext(), "정마담은 " + comjok +" 이었습니다!", Toast.LENGTH_SHORT).show();
        jokbo_Com.setText(comjok);

    }

    public String getjokbo(int pae1, int pae2) {
        if(pae1 > pae2) {
            return getjokbo(pae2, pae1);
        } else {
            String s = "";
            int m1 = (pae1 + 2)/2;
            int m2 = (pae2 + 2)/2;
            if(pae1 == 5 && pae2 == 15) {
                s = "38광땡";
            } else if(pae1 == 1 && pae2 == 15) {
                s = "18광땡";
            } else if(pae1 == 1 && pae2 == 5) {
                s = "13광땡";
            } else if(m1 == m2) {
                switch(m1) {
                    case 1 :
                        s = "1땡";
                        break;
                    case 2 :
                        s = "2땡";
                        break;
                    case 3 :
                        s = "3땡";
                        break;
                    case 4 :
                        s = "4땡";
                        break;
                    case 5 :
                        s = "5땡";
                        break;
                    case 6 :
                        s = "6땡";
                        break;
                    case 7 :
                        s = "7땡";
                        break;
                    case 8 :
                        s = "8땡";
                        break;
                    case 9 :
                        s = "9땡";
                        break;
                    case 10 :
                        s = "장땡";
                        break;
                }
            } else if(m1 == 1 && m2 == 2) {
                s = "알리";
            } else if(m1 == 1 && m2 == 4) {
                s = "독사";
            } else if(m1 == 1 && m2 == 9) {
                s = "구삥";
            } else if(m1 == 1 && m2 == 10) {
                s = "장삥";
            } else if(m1 == 4 && m2 == 10) {
                s = "장사";
            } else if(m1 == 4 && m2 == 6) {
                s = "세륙";
            } else if(pae1 == 7 && pae2 == 13) {
                s = "암행어사";
            } else if(pae1 == 5 && pae2 == 13) {
                s = "땡잡이";
            } else if(pae1 == 7 && pae2 == 16) {
                s = "멍텅구리 구사";
            } else if(m1 == 4 && m2 == 9) {
                s = "구사";
            } else {
                switch ((m1 + m2)%10) {
                    case 0 : s = "망통";
                        break;
                    case 1 : s = "한끗";
                        break;
                    case 2 : s = "두끗";
                        break;
                    case 3 : s = "세끗";
                        break;
                    case 4 : s = "네끗";
                        break;
                    case 5 : s = "다섯끗";
                        break;
                    case 6 : s = "여섯끗";
                        break;
                    case 7 : s = "일곱끗";
                        break;
                    case 8 : s = "여덟끗";
                        break;
                    case 9 : s = "갑오";
                        break;
                }
            }
            return s;
        }
    }

    public String convert(long i) {
        String s = "";
        if(i==0) {
            return "0원";
        }
        long uk = 100000000;
        long man = 10000;
        if(i/uk != 0) {
            s = s + Long.toString(i/uk) + "억 ";
            i = i%uk;
        }
        if(i/man != 0) {
            s = s + Long.toString(i/man) + "만 ";
            i = i%man;
        }
        if(i != 0) {
            return s + Long.toString(i) + "원";
        } else {
            return s + "원";
        }
    }

    public int[] randint() {
        Random rnd = new Random();
        int a[] = new int[4];
        for (int i = 0 ; i < 4 ; i ++ ) {
            a[i] = rnd.nextInt(20);
            for(int j = 0 ; j < i; j ++ ) {
                if(a[i] == a[j]) {
                    i --;
                }
            }
        }
        return a;

    }
}
