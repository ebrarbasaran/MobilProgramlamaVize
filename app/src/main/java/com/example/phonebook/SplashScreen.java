package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView txtYazi;
    private static int gecisSuresi=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        txtYazi = (TextView) findViewById(R.id.txt_yazi);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gecis = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(gecis);
                finish();
            }
        },gecisSuresi);
    }
}