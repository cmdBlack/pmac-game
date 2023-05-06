package com.pmac.zero;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity{

    public Button btnSetting, btnExit, btnStart, btnBlack, btnVile;
    public  ImageButton imgBtnS;
    public EditText editTextUser, editTextPassword;
    public ImageView imageView, imageViewP, imageView3;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video();
        delay();
        secretpage();
        Glide.with(this).load(Uri.parse("android.resource://"+getPackageName()+"/"+ R.drawable.space)).into(imageView);

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        video();
        super.onResume();
    }



    private void delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.GONE);
                imageViewP.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.VISIBLE);
                btnSetting.setVisibility(View.VISIBLE);
                btnExit.setVisibility(View.VISIBLE);
                btnVile.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
            }
        }, 4000);
    }

    private void secretpage() {
        this.btnStart = findViewById(R.id.btnStart);
        this.imageView = findViewById(R.id.imageView);
        this.imageViewP = findViewById(R.id.imageViewP);
        this.btnSetting = findViewById(R.id.btnSetting);
        this.btnExit = findViewById(R.id.btnExit);
        this.imageView3 = findViewById(R.id.imageView3);
        this.imgBtnS = findViewById(R.id.imgBtnS);
        this.btnVile =  findViewById(R.id.btnVile);
        this.editTextUser = findViewById(R.id.editTextUser);
        this.editTextPassword = findViewById(R.id.editTextPassword);
        this.btnBlack = findViewById(R.id.btnBlack);
        btnVile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUser.setVisibility(View.VISIBLE);
                editTextPassword.setVisibility(View.VISIBLE);
                imgBtnS.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "o", Toast.LENGTH_SHORT).show();
            }
        });

        imgBtnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MainActivity.this.editTextUser.getText().toString().equals("faYe09") ||
                        !MainActivity.this.editTextPassword.getText().toString().equals("kaZr09")){
                    Toast.makeText(getApplicationContext(), "NOT FOR YOU", Toast.LENGTH_LONG).show();
                    btnVile.setVisibility(View.GONE);
                    editTextUser.setVisibility(View.GONE);
                    editTextPassword.setVisibility(View.GONE);
                    imgBtnS.setVisibility(View.GONE);
                }
                else if (MainActivity.this.editTextUser.getText().toString().equals("faYe09") &&
                        MainActivity.this.editTextPassword.getText().toString().equals("kaZr09")){
                    Toast.makeText(getApplicationContext(), "SECRET", Toast.LENGTH_LONG).show();
                    btnBlack.setVisibility(View.VISIBLE);
                }
            }
        });

        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ONLY FOR YOU", Toast.LENGTH_SHORT).show();
                MainActivity.this.startActivity(new Intent(MainActivity.this, ActivityVid.class));
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AndroidLauncher.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }

    private void video() {
        String filename = "android.resource://"+getPackageName()+"/"+ R.raw.ee;
        VideoView vv = findViewById(R.id.videoViewBg);vv.setVideoURI(Uri.parse(filename));
        vv.start();
        vv.requestFocus();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });



    }

}
