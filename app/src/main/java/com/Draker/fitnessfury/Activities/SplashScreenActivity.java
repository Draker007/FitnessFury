package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SLASH_TIME_OUT = 4000;
    TextView tv;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv=findViewById(R.id.tv);
        iv=findViewById(R.id.iv);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.mytranstion);
        tv.startAnimation(animation);
        iv.startAnimation(animation);
Thread timer = new Thread(){
    public void run(){
        try{
            sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
finish();

        }
    }
};
timer.start();
    }
}
