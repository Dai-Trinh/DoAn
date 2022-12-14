package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ImageView imgStore;
    EditText edtTimKiem;
    DoAnAdapter doAnAdapter;
    ImageView imgChat;
    ImageView imgThongBao;
    ImageView imgTaiKhoan;
    private final static int FRAGMENT_TATCA = 1;
    private final static int FRAGMENT_DOAN = 2;
    private final static int FRAGMENT_DOUONG = 3;
    private final static int FRAGMENT_DOCHAY = 4;
    private final static int FRAGMENT_BANHKEM = 5;
    private int mFragment = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        doAnAdapter = new DoAnAdapter();
        imgStore = findViewById(R.id.imagebutton_store);
        imgChat = findViewById(R.id.imagebutton_chat);
        edtTimKiem = findViewById(R.id.edit_Timkiem);
        imgThongBao = findViewById(R.id.image_thongbao);
        imgTaiKhoan = findViewById(R.id.imagebutton_user);


        imgTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, TaiKhoan.class));
            }
        });

        imgThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, ThongBaoActivity.class));
            }
        });

        edtTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, TimKiemActivity.class));
            }
        });


        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentChat = new Intent(MainActivity2.this, ChatActivity.class);
                startActivity(intentChat);
            }
        });

        imgStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStore = new Intent(MainActivity2.this, QuanLyActivity.class);
                startActivity(intentStore);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toll_bar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new TatCaFragment());
        navigationView.getMenu().findItem(R.id.nav_tatca).setChecked(true);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_tatca){
            if(mFragment != FRAGMENT_TATCA){
                replaceFragment(new TatCaFragment());
                mFragment = FRAGMENT_TATCA;
            }
        } else if (id == R.id.nav_doan){
            if(mFragment != FRAGMENT_DOAN){
                replaceFragment(new DoAnFragment());
                mFragment = FRAGMENT_DOAN;
            }
        } else if(id == R.id.nav_douong) {
            if(mFragment != FRAGMENT_DOUONG){
                replaceFragment(new DoUongFragment());
                mFragment = FRAGMENT_DOUONG;
            }
        } else if(id == R.id.nav_dochay){
            if(mFragment != FRAGMENT_DOCHAY){
                replaceFragment(new DoChayFragment());
                mFragment = FRAGMENT_DOCHAY;
            }
        } else if(id == R.id.nav_banhkem){
            if(mFragment != FRAGMENT_BANHKEM){
                replaceFragment(new BanhKemFragment());
                mFragment = FRAGMENT_BANHKEM;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}