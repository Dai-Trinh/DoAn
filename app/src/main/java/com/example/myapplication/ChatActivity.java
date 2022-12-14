package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChatActivity extends AppCompatActivity {

    ImageView imgHome, imgQuanLy, imgUserChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        imgHome = findViewById(R.id.imagebutton_home_chat);
        imgQuanLy = findViewById(R.id.imagebutton_store_chat);
        imgUserChat = findViewById(R.id.imagebutton_user_chat);


        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatActivity.this, MainActivity2.class));
            }
        });

        imgQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatActivity.this, QuanLyActivity.class));
            }
        });

        imgUserChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatActivity.this, TaiKhoan.class));
            }
        });
    }
}