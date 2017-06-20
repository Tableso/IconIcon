package com.example.flowers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[][] flowerList = new int[6][10];
    LinearLayout linearLayout;
    View viewForm;
    boolean isSelected = false;
    int selectedFlower;
    int score;
    TextView scoreView;
    int flowersCount=3;
    int last_row;
    int last_col;
    Button restart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        scoreView= (TextView) findViewById(R.id.score);
        restart = (Button) findViewById(R.id.restart);

        creatFlowers();
        init();


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                       for(int i=0;i<6;i++){
                           for(int j=0;j<10;j++){
                               flowerList[i][j]=0;
                           }
                       }

                scoreView.setText("0");
                flowersCount = 3;

                       creatFlowers();
                       viewForm.invalidate();





            }
        });

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    int intX = (int) event.getX();
                    int intY = (int) event.getY();
                    Log.d("flower", String.valueOf(intX)+":"+intY);

                if(intX>30&&intX<1530&&intY>30&&intY<930){

                    int col= (int)((intX-30)/150);
                    int row= (int)((intY-30)/150);

                    if(isSelected==false&&flowerList[row][col]!=0){
                        selectedFlower = flowerList[row][col];
                        last_col=col;
                        last_row=row;
                        flowerList[row][col]=0;
                        isSelected = true;
                    }else if(isSelected==true&&col==last_col&&row==last_row){

                        flowerList[row][col]=selectedFlower;

                        isSelected=false;

                    }else if(isSelected==true&&flowerList[row][col]==0){
                        flowerList[row][col] = selectedFlower;
                        isSelected=false;
                        judge(row,col);
                        creatFlowers();
                        viewForm.invalidate();
                    }
                }






                }
                return MainActivity.super.onTouchEvent(event);
            }
        });








    }


    private void init() {
        LinearLayout layout=(LinearLayout) findViewById(R.id.root);;
        this.linearLayout = layout;
       final DrawView view=new DrawView(this,flowerList);
        this.viewForm = view;
//        view.setMinimumHeight(500);
//        view.setMinimumWidth(300);
        //通知view组件重绘
        view.invalidate();
        layout.addView(view);

    }

    private void creatFlowers(){



        for(int i = 0 ;i<3;){


                int col = (int) (Math.random() * 10);
                int row = (int) (Math.random() * 6);
                if (flowerList[row][col] == 0) {
                    flowerList[row][col] = (int) (Math.random() * 5) + 1;
                    i++;
                    flowersCount = flowersCount + 1;
                    if(flowersCount==60){
                        break;
                    }
                }

        }
    }


    private void judge(int row,int col) {
            int n=0;
            int m=0;
            int old_row=row;
            int old_col=col;

        if(flowersCount==60){
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("游戏结束");
            dialog.setMessage("您的得分是:"+score);
            dialog.setCancelable(false);
            dialog.setPositiveButton("重新开始",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for(int i=0;i<6;i++){
                        for(int j=0;j<10;j++){
                            flowerList[i][j]=0;
                        }
                    }

                    scoreView.setText("0");
                    flowersCount = 3;

                    creatFlowers();
                    viewForm.invalidate();
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        }

            //左右判断
            while(row>0&&flowerList[row-1][col]==flowerList[old_row][old_col]){
                row--;

            }

            while(row<5&&flowerList[row+1][col]==flowerList[old_row][old_col]){
                n++;
                row++;
            }

            //纵向判断
            while(col>0&&flowerList[row][col-1]==flowerList[old_row][old_col]){
                col--;
            }

            while(col<9&&flowerList[row][col+1]==flowerList[old_row][old_col]){
                m++;
                col++;
            }

            if(n>=3){
                for(int k=n;k>=0;k--){
                    flowerList[row][col]=0;
                    row--;

                    score=score+n*n;
                }
                flowersCount=flowersCount-n-1;
            }
            Log.d("flower",flowersCount+"纵向有:"+n+"个");

            if(m>=3){
                for(int k=m;k>=0;k--){
                    flowerList[row][col]=0;
                    col--;

                    score=score+m*m;
                }
                flowersCount=flowersCount-m-1;
            }

             Log.d("flower",flowersCount+"横向有:"+m+"个");
        Log.d("flower","总共有花:"+flowersCount+"个.");

        scoreView.setText(String.valueOf(score));
        scoreView.invalidate();

    }


}
