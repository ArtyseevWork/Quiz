package space.aseev.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    public int numLeft;     //left image + text
    public int numRight;    //right image + text
    public int count = 1;   // count of right answers
    Array array = new Array();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        /*******Переопределение строковой переменной*******/
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);
        /***** !Переопределение строковой переменной ******/

        /*******Скругляем углы*******/
         final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);
        /***** !Скругляем углы******/

        /********** animation ************/
        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);
        /********* !animation ************/

        /******fullscreen mode*******/
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        /*** ! fullscreen mode*******/

        /********* Button back********/
        Button buttonBack = (Button) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });
        /****** !Button back********/

        /********* Run dialog at start ********/
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();


        /********* Button dialog-close********/
        TextView btn_close = (TextView) dialog.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    //dialog.dismiss();
                } catch (Exception e) {
                }
            }
        });

        Button buttonContinue = (Button) dialog.findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.hide();
                } catch (Exception e) {
                }
            }
        });
        /****** !dialog-close********/


        /*******  load task *************/
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]);
        TextView text_left = findViewById(R.id.text_left);
        text_left.setText(array.text1[numLeft]);

        numRight = random.nextInt(10);
        while (numRight == numLeft){
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images1[numRight]);
        TextView text_right = findViewById(R.id.textRight);
        text_right.setText(array.text1[numRight]);
        /*******! load task *************/

        /******** user's choise ***********/
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.done);
                        count++ ;
                    }
                    else{
                        img_left.setImageResource(R.drawable.failed);
                        count = count-2;
                        if (count < 0) {
                            count = 0;
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    for (int i = 0; i < 19; i++) {
                        TextView tv = findViewById(array.progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }
                    for (int i = 0; i < count; i++) {
                        TextView tv = findViewById(array.progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                    if (count == 20) {
                        //next level
                    } else {
                        /*******  load task *************/
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        TextView text_left = findViewById(R.id.text_left);
                        text_left.setText(array.text1[numLeft]);

                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        TextView text_right = findViewById(R.id.textRight);
                        text_right.setText(array.text1[numRight]);
                        img_right.setEnabled(true);
                        /*******! load task *************/
                    }
                }
                return true;
            }
        });


        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if (numLeft < numRight){
                        img_right.setImageResource(R.drawable.done);
                        count++ ;
                    }
                    else{
                        img_right.setImageResource(R.drawable.failed);
                        count = count-2;
                        if (count < 0) {
                            count = 0;
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    for (int i = 0; i < 19; i++) {
                        TextView tv = findViewById(array.progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }
                    for (int i = 0; i < count; i++) {
                        TextView tv = findViewById(array.progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                    if (count == 20) {
                        //next level
                    } else {
                        /*******  load task *************/
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        TextView text_left = findViewById(R.id.text_left);
                        text_left.setText(array.text1[numLeft]);

                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        TextView text_right = findViewById(R.id.textRight);
                        text_right.setText(array.text1[numRight]);
                        img_left.setEnabled(true);
                        /*******! load task *************/
                    }
                }
                return true;
            }
        });


        /******* !user's choise ***********/


    }

    /***System button Back***/
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }
    /*** !System button Back****/
}