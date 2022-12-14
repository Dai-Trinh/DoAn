package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ThongBaoActivity extends AppCompatActivity {
    ImageView imgThongBaoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        imgThongBaoBack = findViewById(R.id.thongbao_back);
        imgThongBaoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongBaoActivity.this, MainActivity2.class));
            }
        });
    }
}