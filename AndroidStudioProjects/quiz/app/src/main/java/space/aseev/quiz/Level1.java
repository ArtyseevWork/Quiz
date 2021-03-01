package space.aseev.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    public int numLeft;     //left image + text
    public int numRight;    //right image + text
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
                    Intent intent = new Intent(Level1.this, GameLevels.class);
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
                    Intent intent = new Intent(Level1.this, GameLevels.class);
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
    }

    /*System button Back*/
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }
    /* !System button Back*/
}