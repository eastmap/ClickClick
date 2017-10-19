package k938145.clickclick;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //1. XML에서 뷰 배치를 완료할 것.
    //          리니어레이아웃-버티컬, 텍뷰, 텍뷰, 이미지버튼, 레이아웃4, 이미지버튼4*3
    //          레이아웃패딩, 버튼간 마진
    //2. 동작: 메인화면-버튼 대기, 12개 이미지-식별자 묶음 랜덤배치,
    //          12개 버튼에 이미지 식별자 묶음 대응
    //          지명변수와 일치하는 식별자가진 버튼 클릭시 버튼인비저블
    //          지명변수 범위오버시 스테이지변경 함수 호출
    //3. 변수: 정수배열[12], 이미지배열[12], 버튼대응배열[12], 지명변수, 스테이지변수
    //4. 함수: 초기화함수(메인화면 및 변수 초기화), 정수배열 랜덤정렬, 온클릭, 스테이지변경함수
    //aaaaaa

    //변수들
    int[] nums= new int[12];

    ImageButton[] btns= new ImageButton[12];
    int cnt = 1;
    int stage= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //버튼 받아오기
        getBtns();

        //랜덤정렬생성 호출
        rndSort(nums);

        new ImageButton(this);

        //초기화함수 호출
        init(stage);
    }


    //버튼 받아오기
    void getBtns(){
        for(int i=0; i<btns.length; i++){
            btns[i]=((ImageButton) findViewById(R.id.btn11+i));
        }
    }

    //랜덤정렬 생성
    void rndSort(int[] nums){
        Random rnd= new Random();
        int rndNum=0;
        int tmp=0;
        for(int i=0; i<nums.length; i++){
            nums[i]=i;
        }
        for(int i=0; i<nums.length; i++){
            rndNum=rnd.nextInt(nums.length);
            tmp=nums[i];//보관
            nums[i]=nums[rndNum];//당겨옴
            nums[rndNum]=tmp;//옮겨담음
        }
    }



    //초기화함수
    void init(int stage){
        //배경세팅
        LinearLayout layout= (LinearLayout) findViewById(R.id.mainLayout);
        switch (stage){
            case 1:
                layout.setBackgroundResource(R.drawable.bg1);
                break;
            case 2:
                layout.setBackgroundResource(R.drawable.bg2);
                break;
            case 3:
                layout.setBackgroundResource(R.drawable.bg3);
                break;
        }

        //카드 세팅
        for(int i=0; i<btns.length; i++){
            btns[i].setTag(nums[i]+1);
            btns[i].setBackgroundColor(Color.TRANSPARENT);
            switch (stage){
                case 1:
                    btns[i].setImageResource(R.drawable.num01+nums[i]);
                    break;
                case 2:
                    btns[i].setImageResource(R.drawable.alpa01+nums[i]);
                    break;
                case 3:
                    btns[i].setImageResource(R.drawable.cha01+nums[i]);
                    break;
            }
            btns[i].setVisibility(View.VISIBLE); //다시 보이게
            cnt=1; //카운트 초기화
        }
    }

    //온클릭
    public void clickBtn(View v){

        if(v instanceof ImageButton){
            ImageButton btn= (ImageButton) v;
            if(Integer.parseInt(btn.getTag().toString())==cnt){
                btn.setVisibility(View.INVISIBLE);
                cnt++;
                //조건 달성시 스테이지 업
                if(cnt>12){
                    stage=stage%3+1;
                    init(stage);
                }
            }
        }

    }
}
