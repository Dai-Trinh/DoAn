package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class QuanLyActivity extends AppCompatActivity {

    ImageView imgHome;
    ImageView imgChat, imgUserQuanLi;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);
        imgHome = findViewById(R.id.imagebutton_home_quanli);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        imgChat = findViewById(R.id.imagebutton_chat_quanli);
        imgUserQuanLi = findViewById(R.id.imagebutton_user_quanli);

        imgUserQuanLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyActivity.this, TaiKhoan.class));
            }
        });

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentChat = new Intent(QuanLyActivity.this, ChatActivity.class);
                startActivity(intentChat);
            }
        });


        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(QuanLyActivity.this, MainActivity2.class);
                startActivity(intentHome);
            }
        });

        ViewPagerApdapter viewPagerApdapter = new ViewPagerApdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerApdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}