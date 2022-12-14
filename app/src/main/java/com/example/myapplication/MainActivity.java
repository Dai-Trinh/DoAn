package com.example.myapplication;

import static com.example.myapplication.DangKyActivity.strMK;
import static com.example.myapplication.DangKyActivity.strTDN;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap;
    TextView txtDangKy;
    EditText edtTenDangNhap;
    EditText edtPass;
    String strEmailDN = null;
    String passDN = null;
    DangKyMoi dangKyMoi2;
    ProgressDialog progressDialog;
    public static String strTenDN = null;
    public String strMatKhau;
    CheckBox luuMatKhau;
    int check;
    TaiKhoanSQLite taiKhoanSQLite;
    String tenDN2;
    String matkhauDN2;
    int c2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangNhap = findViewById(R.id.button_danhnhap);
        txtDangKy = findViewById(R.id.text_dangky);
        edtTenDangNhap = findViewById(R.id.edit_tendangnhap1);
        edtPass = findViewById(R.id.edit_matkhau1);
        progressDialog = new ProgressDialog(this);
        luuMatKhau = findViewById(R.id.check_dangnhap);
        taiKhoanSQLite = new TaiKhoanSQLite(this, "taikhoan.sqlite", null, 1);

        taiKhoanSQLite.QueryData("CREATE TABLE IF NOT EXISTS TaiKhoan(Id INTEGER PRIMARY KEY, TenDangNhap CHAR(200), MatKhau CHAR(50), DieuKien INTEGER)");
        //taiKhoanSQLite.QueryData("INSERT INTO TaiKhoan VALUES (1, null, null, 1)");
        LayTaiKhoan();
        onClickDangNhap();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(luuMatKhau.isChecked()){
                    check = 0;
                } else {
                    check = 1;
                }
                onClickDangNhap();
                checkDN();
            }
        });

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDangKi = new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(intentDangKi);
            }
        });


    }

    private void onClickDangNhap() {
        progressDialog.show();
        strTenDN = edtTenDangNhap.getText().toString().trim();
        strMatKhau = edtPass.getText().toString().trim();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = firebaseDatabase.getReference(strTenDN);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dangKyMoi2 = snapshot.getValue(DangKyMoi.class);
                strEmailDN = dangKyMoi2.getStrTenDK();
                passDN = dangKyMoi2.getStrMKDK();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        if(strEmailDN == null || passDN == null ){
//            progressDialog.dismiss();
//            Toast.makeText(this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
//        } else {
//            FirebaseAuth auth = FirebaseAuth.getInstance();
//            auth.signInWithEmailAndPassword(strEmailDN, passDN)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            progressDialog.dismiss();
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                                startActivity(intent);
//
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        }
    }

    void checkDN() {
        if (strEmailDN == null || passDN == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
        } else if (strEmailDN.equals(strTenDN) && strMatKhau.equals(passDN)) {
            UpdateTaiKhoan(edtTenDangNhap.getText().toString().trim(), edtPass.getText().toString().trim(), check);
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        } else {
            progressDialog.dismiss();
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
        }
    }

    void UpdateTaiKhoan(String strTenDN1, String strMatKhau1, int c1){
        taiKhoanSQLite.QueryData("UPDATE TaiKhoan SET TenDangNhap = '" + strTenDN1 + "', MatKhau = '" + strMatKhau1 + "', DieuKien = '" + c1 +"' WHERE Id = '" + 1 + "'");
    }

    void LayTaiKhoan(){
        Cursor TaiKhoanDangNhap = taiKhoanSQLite.GetData("SELECT * FROM TaiKhoan");
        while (TaiKhoanDangNhap.moveToNext()){
            int id = TaiKhoanDangNhap.getInt(0);
            tenDN2 = TaiKhoanDangNhap.getString(1);
            matkhauDN2 = TaiKhoanDangNhap.getString(2);
            c2 = TaiKhoanDangNhap.getInt(3);
        }
        if(c2 == 0){
            edtTenDangNhap.setText(tenDN2);
            edtPass.setText(matkhauDN2);
            luuMatKhau.setChecked(true);
        } else {
            edtTenDangNhap.setText("");
            edtPass.setText("");
            luuMatKhau.setChecked(false);
        }

    }

}