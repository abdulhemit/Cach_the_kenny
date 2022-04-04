package com.example.cachthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textTime;
    TextView textScore;
    int score ;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTime = findViewById(R.id.time);
        textScore = findViewById(R.id.score);
         imageView1 = findViewById(R.id.imageView1);
         imageView2 = findViewById(R.id.imageView2);
         imageView3 = findViewById(R.id.imageView3);
         imageView4 = findViewById(R.id.imageView4);
         imageView5 = findViewById(R.id.imageView5);
         imageView6 = findViewById(R.id.imageView6);
         imageView7 = findViewById(R.id.imageView7);
         imageView8 = findViewById(R.id.imageView8);
         imageView9 = findViewById(R.id.imageView9);
         imageView10 = findViewById(R.id.imageView10);
         imageView11 = findViewById(R.id.imageView11);
         imageView12 = findViewById(R.id.imageView12);

         imageArray = new ImageView[]{
                 imageView1,imageView2,imageView3,imageView4,imageView5,
                 imageView6,imageView7,imageView8,imageView9,imageView10,
                 imageView11,imageView12
         };
         hideImage();


        score = 0;
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                textTime.setText("Time: "+ l /1000);
            }

            @Override
            public void onFinish() {

                textTime.setText("Time off");
                handler.removeCallbacks(runnable);
                for (ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Restart");
                alertDialog.setMessage("Are you sure to restart game?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game over",Toast.LENGTH_LONG).show();

                    }
                });
                alertDialog.show();

            }
        }.start();

    }
    public void increasScore(View view){
        score++;
        textScore.setText("Score: "+score);
    }
    public void hideImage(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(12);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);

    }
}