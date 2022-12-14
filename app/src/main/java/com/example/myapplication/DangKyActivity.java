package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class DangKyActivity extends AppCompatActivity {

    EditText edtTenDangNhap, edtTenHienThi, edtEmail, edtSoDienThoai, edtMatKhau, edtXNMatKhau;
    Button btnDangKy, btnHuy;
    DangKyMoi dangKyMoi1;
    String DKMoi = null;
    public static String strTDN = null, strMK = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();
        iListener();
    }

    private void iListener() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDangKy();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(DangKyActivity.this, MainActivity.class);
                startActivity(intentHuy);
            }
        });
    }

    private void AnhXa() {
        edtTenDangNhap = findViewById(R.id.edt_tendangnhap);
        edtTenHienThi = findViewById(R.id.edt_tenhienthi);
        edtEmail = findViewById(R.id.edt_email);
        edtSoDienThoai = findViewById(R.id.edt_sodienthoai);
        edtMatKhau = findViewById(R.id.edt_matkhau);
        edtXNMatKhau = findViewById(R.id.edt_xacnhanmatkhau);
        btnDangKy = findViewById(R.id.button_dangky);
        btnHuy = findViewById(R.id.button_huydangky);
    }

    private void onClickDangKy(){
        String strEmail = edtEmail.getText().toString().trim();
        String strMatKhau = edtMatKhau.getText().toString().trim();
        String strXacNhanMK = edtXNMatKhau.getText().toString().trim();
        String strTenDN = edtTenDangNhap.getText().toString().trim();
        String strTenHienThi = edtTenHienThi.getText().toString();
        String strSDT = edtSoDienThoai.getText().toString().trim();

        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
        DatabaseReference my1 = firebaseDatabase1.getReference(strTenDN);
        my1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dangKyMoi1 = snapshot.getValue(DangKyMoi.class);
                DKMoi = dangKyMoi1.getStrTenDK();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(strEmail.isEmpty() || strMatKhau.isEmpty() || strXacNhanMK.isEmpty() || strTenDN.isEmpty() || strTenHienThi.isEmpty() || strSDT.isEmpty()){
            Toast.makeText(this, "Bạn phải nhập thông tin đầy đủ!", Toast.LENGTH_SHORT).show();
        } else if(strTDN == DKMoi) {
            Toast.makeText(DangKyActivity.this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
        }
        else {
            if(!(strMatKhau.equals(strXacNhanMK))){
                Toast.makeText(this, "Nhập lại mật khẩu!", Toast.LENGTH_SHORT).show();
            } else {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(strEmail, strMatKhau)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DangKyActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                    strTDN = strTenDN;
                                    strMK = strMatKhau;
                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference = firebaseDatabase.getReference(strTenDN);
                                    DangKyMoi dangKyMoi = new DangKyMoi(strTenDN, strTenHienThi, strEmail, strSDT, strMatKhau);
                                    databaseReference.setValue(dangKyMoi);
                                    Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    Toast.makeText(DangKyActivity.this, "Đăng kí không thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
            }

    }

}