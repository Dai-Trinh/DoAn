package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {

    TextView txtTenDN, txtTenHT, txtEmail, txtSoDienThoai, txtMatKhau;
    public static DangKyMoi dangKyMoi;
    Button btnSuaThongTin;
    ImageView imgThongTinTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);

        AnhXa();
        ThongTin();

        imgThongTinTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongTinTaiKhoanActivity.this, TaiKhoan.class));
            }
        });


        btnSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuaThongTinDialog();

            }
        });
    }

    private void AnhXa(){
        txtTenDN = findViewById(R.id.text_tendangnhap);
        txtTenHT = findViewById(R.id.text_tenhienthi);
        txtEmail = findViewById(R.id.text_email);
        txtSoDienThoai = findViewById(R.id.text_sodienthoai);
        txtMatKhau = findViewById(R.id.text_matkhau);
        btnSuaThongTin = findViewById(R.id.button_suathongtintaikhoan);
        imgThongTinTroVe = findViewById(R.id.img_thongtintaikhoan_trove);
    }

    private void SuaThongTinDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_sua_thong_tin);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        EditText edtTenHT = dialog.findViewById(R.id.edit_tenhienthi);
        EditText edtEmailSua = dialog.findViewById(R.id.edit_emailsua);
        EditText edtSDTsua = dialog.findViewById(R.id.edit_sodienthoaisua);
        Button btnCapNhatSua = dialog.findViewById(R.id.button_capnhatsua);
        Button btnHuySua = dialog.findViewById(R.id.button_huysua);

        edtTenHT.setText(dangKyMoi.getStrTenHT());
        edtEmailSua.setText(dangKyMoi.getStrEmailDK());
        edtSDTsua.setText(dangKyMoi.getStrSDTDK());

        btnCapNhatSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKyMoi.setStrTenHT(edtTenHT.getText().toString().trim());
                dangKyMoi.setStrEmailDK(edtEmailSua.getText().toString().trim());
                dangKyMoi.setStrSDTDK(edtSDTsua.getText().toString().trim());

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference(MainActivity.strTenDN);
                databaseReference.setValue(dangKyMoi);
                Toast.makeText(ThongTinTaiKhoanActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                ThongTin();
                dialog.dismiss();
            }
        });

        btnHuySua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void ThongTin(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(MainActivity.strTenDN);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dangKyMoi = snapshot.getValue(DangKyMoi.class);
                txtTenHT.setText(dangKyMoi.getStrTenHT());
                txtTenDN.setText(dangKyMoi.getStrTenDK());
                txtEmail.setText(dangKyMoi.getStrEmailDK());
                txtSoDienThoai.setText(dangKyMoi.getStrSDTDK());
                txtMatKhau.setText(dangKyMoi.getStrMKDK());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}