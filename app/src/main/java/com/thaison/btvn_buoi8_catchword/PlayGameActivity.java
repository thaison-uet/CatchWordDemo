package com.thaison.btvn_buoi8_catchword;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Shin on 7/23/2016.
 */
public class PlayGameActivity extends Activity implements View.OnClickListener {
    private static final String TAG = PlayGameActivity.class.getSimpleName();
    private static final String TRUE = "Bạn đã tìm ra đáp án đúng!";
    private static final String FALSE = "Bạn đã chọn sai đáp án rồi!";
    private static final int MIN_CHARACTER_CODE = 65;
    private static final int MAX_CHARACTER_CODE = 90;

    private int level;
    private int heart;
    private int gold;
    private Random random;
    private String answer;
    private String[] answers;
    private String[] answerLetters;
    private List<Integer> btnHoverList;
    private List<Button> btnChosenList;
    private String chosenButtonString = new String();
    private String result;

    private TextView tvHeart;
    private TextView tvGold;
    private Button btnNext;
    private ImageView ivImage;
    private TextView tvResult;
    private Button[] btnHover;
    private Button[] btnChosen;
    private int imageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate..");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initViews();
        initComponents();
        setEvents();
    }

    private void initViews() {
        tvHeart = (TextView) findViewById(R.id.tv_heart);
        tvGold = (TextView) findViewById(R.id.tv_gold);
        btnNext = (Button) findViewById(R.id.btn_next);
        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnHover = new Button[16];
        int resIDBtnHover;
        for (int i = 0; i < 16; i++) {
            resIDBtnHover = getResources().getIdentifier("btn_tile_hover_" + i, "id", getPackageName());
            btnHover[i] = (Button) findViewById(resIDBtnHover);
        }
        btnChosen = new Button[16];
        int resIDBtnChosen;
        for (int i = 0; i < 16; i++) {
            resIDBtnChosen = getResources().getIdentifier("btn_tile_chosen_" + i, "id", getPackageName());
            btnChosen[i] = (Button) findViewById(resIDBtnChosen);
            btnChosen[i].setVisibility(View.INVISIBLE);
        }
    }

    private void initComponents() {
        int i;
        level = 1;
        heart = 5;
        tvHeart.setText(heart + "");
        gold = 0;
        tvGold.setText(gold + "");
        random = new Random();
        answers = new String[29];
        answers[0] = "aomua";
        answers[1] = "baocao";
        answers[2] = "canthiep";
        answers[3] = "cattuong";
        answers[4] = "chieutre";
        answers[5] = "danhlua";
        answers[6] = "danong";
        answers[7] = "giangmai";
        answers[8] = "hoidong";
        answers[9] = "hongtam";
        answers[10] = "khoailang";
        answers[11] = "kiemchuyen";
        answers[12] = "lancan";
        answers[13] = "masat";
        answers[14] = "nambancau";
        answers[15] = "oto";
        answers[16] = "quyhang";
        answers[17] = "songsong";
        answers[18] = "thattinh";
        answers[19] = "thothe";
        answers[20] = "tichphan";
        answers[21] = "tohoai";
        answers[22] = "totien";
        answers[23] = "tranhthu";
        answers[24] = "vuaphaluoi";
        answers[25] = "vuonbachthu";
        answers[26] = "xakep";
        answers[27] = "xaphong";
        answers[28] = "xedapdien";
        //init array of answers characters
        imageNumber = random.nextInt(29);
        answerLetters = answers[imageNumber].split("");
        answer = answers[imageNumber];
        //init number of all button to list
        btnHoverList = new ArrayList<>();
        for (i = 0; i < btnHover.length; i++) {
            btnHoverList.add(i);
        }
        // add random image to ivImage
        ivImage.setImageResource(getResources().getIdentifier("img_" + answers[imageNumber],
                "drawable", getPackageName()));
        // fill all character of answer to different btn_tile_hover
        int buttonHoverIndex;
        for (i = 1; i < answerLetters.length; i++) {
            buttonHoverIndex = random.nextInt(btnHoverList.size());
            btnHover[btnHoverList.get(buttonHoverIndex)].setText(answerLetters[i]);
            btnHoverList.remove(buttonHoverIndex);
        }
        // fill random character from alphabet to all un-fill btn_tile_hover
        while (!btnHoverList.isEmpty()) {
            buttonHoverIndex = random.nextInt(btnHoverList.size());
            btnHover[btnHoverList.get(buttonHoverIndex)].setText(Character.toString((char)
                    (random.nextInt(MAX_CHARACTER_CODE - MIN_CHARACTER_CODE) + MIN_CHARACTER_CODE)));
            btnHoverList.remove(buttonHoverIndex);
        }

        // init list of chosen button
        btnChosenList = new ArrayList<>();
        btnChosenList.add(btnChosen[6]);
        btnChosenList.add(btnChosen[4]);
        btnChosenList.add(btnChosen[2]);
        btnChosenList.add(btnChosen[0]);
        btnChosenList.add(btnChosen[1]);
        btnChosenList.add(btnChosen[3]);
        btnChosenList.add(btnChosen[5]);
        btnChosenList.add(btnChosen[7]);
        btnChosenList.add(btnChosen[9]);
        btnChosenList.add(btnChosen[14]);
        btnChosenList.add(btnChosen[12]);
        btnChosenList.add(btnChosen[10]);
        btnChosenList.add(btnChosen[8]);
        btnChosenList.add(btnChosen[11]);
        btnChosenList.add(btnChosen[13]);
        btnChosenList.add(btnChosen[15]);
        // set amount of btn_tile_chosen is visible
        for (i = 0; i < answerLetters.length - 1; i++) {
            btnChosen[i].setVisibility(View.VISIBLE);
        }
    }

    private void setEvents() {
        int i;
        final List<Button> btnChosenInvisibleList = new ArrayList<>();
        i = 0;
        while (i < answerLetters.length - 1) {
            if (answerLetters.length - 1 <= 8) {
                Log.i(TAG, "here1");
                if (answerLetters.length >= 1 && answerLetters.length <= 2) {
                    btnChosenInvisibleList.add(btnChosenList.get(i + 3));
                } else {
                    if (answerLetters.length >= 3 && answerLetters.length <= 4) {
                        btnChosenInvisibleList.add(btnChosenList.get(i + 2));
                    } else {
                        if (answerLetters.length >= 5 && answerLetters.length <= 6) {
                            btnChosenInvisibleList.add(btnChosenList.get(i + 1));
                        } else {
                            btnChosenInvisibleList.add(btnChosenList.get(i + 0));
                        }
                    }
                }
            } else {
                Log.i(TAG, "here2");
                if (answerLetters.length - 1 >= 9 && answerLetters.length <= 10) {
                    btnChosenInvisibleList.add(btnChosenList.get(i + 3));
                } else {
                    if (answerLetters.length >= 11 && answerLetters.length <= 12) {
                        btnChosenInvisibleList.add(btnChosenList.get(i + 2));
                    } else {
                        if (answerLetters.length >= 13 && answerLetters.length <= 14) {
                            btnChosenInvisibleList.add(btnChosenList.get(i + 1));
                        } else {
                            btnChosenInvisibleList.add(btnChosenList.get(i + 0));
                        }
                    }
                }
            }
            i++;
            Log.i(TAG, "here");
        }
        Toast.makeText(PlayGameActivity.this, btnChosenInvisibleList.size() + "", Toast.LENGTH_SHORT).show();
        btnNext.setOnClickListener(this);
        final int[] btnChosenListIndex = {0};
        for (i = 0; i < 16; i++) {
            final int elementBtnHover = i;
            btnHover[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (btnChosenListIndex[0] <= btnChosenInvisibleList.size() - 1) {
                        chosenButtonString += btnHover[elementBtnHover].getText();
                        btnChosenInvisibleList.get(btnChosenListIndex[0]).setText(
                                btnHover[elementBtnHover].getText());
                        btnHover[elementBtnHover].setVisibility(View.INVISIBLE);
                        btnChosenListIndex[0]++;
                        if (btnChosenListIndex[0] == btnChosenInvisibleList.size()) {
                            if (chosenButtonString.equals(answer)) {
                                Toast.makeText(PlayGameActivity.this, "WIN", Toast.LENGTH_SHORT).show();
                                btnNext.setVisibility(View.VISIBLE);
                                for (int j = 0; j < 16; j++) {
                                    btnChosen[j].setBackgroundResource(R.drawable.ic_tile_true);
                                }
                            } else {
                                Toast.makeText(PlayGameActivity.this, "LOSE", Toast.LENGTH_SHORT).show();
                                for (int j = 0; j < 16; j++) {
                                    btnChosen[j].setBackgroundResource(R.drawable.ic_tile_false);
                                }
                            }
                        }
                    }
                }
            });
        }
        for (i = 0; i < 15; i++) {
            final int elementBtnChosen = i;
            btnChosen[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnChosen[elementBtnChosen].setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        initViews();
        initComponents();
        setEvents();
    }
}
