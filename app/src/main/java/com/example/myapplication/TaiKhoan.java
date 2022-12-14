package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TaiKhoan extends AppCompatActivity {

    TextView ThongTinTK, txtLogOut, txtBanDo;
    ImageView imgHomeTT, imgQuanLiTT, imgChatTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);

        ThongTinTK = findViewById(R.id.thongtintaikhoan);
        imgHomeTT = findViewById(R.id.imagebutton_home_tt);
        imgQuanLiTT = findViewById(R.id.imagebutton_store_tt);
        imgChatTT = findViewById(R.id.imagebutton_chat_tt);
        txtLogOut = findViewById(R.id.taikhoan_logout);
        txtBanDo = findViewById(R.id.taikhoan_bando);

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLogout();
            }
        });

        imgHomeTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaiKhoan.this, MainActivity2.class));
            }
        });

        imgQuanLiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaiKhoan.this, QuanLyActivity.class));
            }
        });

        imgChatTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaiKhoan.this, ChatActivity.class));
            }
        });

        ThongTinTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaiKhoan.this, ThongTinTaiKhoanActivity.class));
            }
        });

        txtBanDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaiKhoan.this, MapsActivity.class));
            }
        });
    }

    private void DialogLogout(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có muốn đăng xuất không!");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(TaiKhoan.this, MainActivity.class));
            }
        });

        b.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog al = b.create();
        al.show();

    }

}