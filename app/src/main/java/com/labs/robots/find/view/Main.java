package com.labs.robots.find.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hanks.htextview.fade.FadeTextView;
import com.labs.robots.find.R;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main2);

        FadeTextView typerTextView = (FadeTextView) findViewById(R.id.textView);
        typerTextView.animateText("Find - поиск  исполнителей для вашего бизнеса"); //


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

}
