package com.example.materialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView img1;

    private static final long SPLASH_SCREEN_DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.imgView);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent().setClass(
                        MainActivity.this,InicioActivity.class);
                startActivity(i);
                //desaparecer();
               // dilatacion();
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,SPLASH_SCREEN_DELAY);

    }

    public void desaparecer() {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.desaparecer);
        animation.setFillAfter(true);
        img1.startAnimation(animation);

    }
   // public void dilatacion() {
     //   Animation animation = AnimationUtils.loadAnimation(this,R.anim.dilatacion);
       // animation.setFillAfter(true);
       // img1.startAnimation(animation);
    //}
}