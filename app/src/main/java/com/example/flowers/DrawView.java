package com.example.flowers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class DrawView extends View {

    int[][] flowerList = new int[6][10];

    public DrawView(Context context,int[][] flowerList) {
        super(context);
        this.flowerList = flowerList;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        //定义DisplayMetrics 对象
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;
        */

        Paint p = new Paint();
        p.setColor(Color.GRAY);




        //画横线
        for(int i = 0 ;i<9;i++){
            canvas.drawLine(30,i*150+30,1530,i*150+30,p);
        }

        //画竖线
        for(int i = 0 ;i<11;i++){
            canvas.drawLine(i*150+30,30,i*150+30,930,p);
        }



        //画外框
        canvas.drawLine(27,27,1533,27,p);
        canvas.drawLine(27,27,27,933,p);
        canvas.drawLine(27,933,1533,933,p);
        canvas.drawLine(1533,27,1533,933,p);

        drawFlower(canvas);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void drawFlower(Canvas canvas){

        float scale = (float)100/288;

        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_btn_speak_now);
        Bitmap mbitmap = Bitmap.createScaledBitmap(bitmap,150,150,true);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_dialog_alert_holo_light);
        Bitmap mbitmap1 = Bitmap.createScaledBitmap(bitmap1,150,150,true);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_airplane_mode);
        Bitmap mbitmap2 = Bitmap.createScaledBitmap(bitmap2,150,150,true);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_lock);
        Bitmap mbitmap3 = Bitmap.createScaledBitmap(bitmap3,150,150,true);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_send);
        Bitmap mbitmap4 = Bitmap.createScaledBitmap(bitmap4,150,150,true);




        Paint paint = new Paint();
        for(int i = 0 ; i<6;i++){
            for(int j=0;j<10;j++){

                switch (flowerList[i][j]){
                    case 1:



                        canvas.drawBitmap(mbitmap,j*150+30,i*150+30,paint);
                        Log.d("flower",""+bitmap.getWidth());
                        break;
                    case 2:
                        canvas.drawBitmap(mbitmap1,j*150+30,i*150+30,paint);
                        break;
                    case 3:
                        canvas.drawBitmap(mbitmap2,j*150+30,i*150+30,paint);
                        break;
                    case 4:
                        canvas.drawBitmap(mbitmap3,j*150+30,i*150+30,paint);
                        break;
                    case 5:
                        canvas.drawBitmap(mbitmap4,j*150+30,i*150+30,paint);
                        break;
                    default:
                        break;
                }
            }



//            if(flowerList[i] == 0){
//
//            }
        }

    }

}
