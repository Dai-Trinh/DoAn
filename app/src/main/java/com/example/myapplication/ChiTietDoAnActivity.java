package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChiTietDoAnActivity extends AppCompatActivity {

    ImageView imgAnhDoAn, imgTroVeCT, imgTimKiemCT;
    TextView txtTenDoAn, txtGia, txtDiaChi, txtTrangThai, txtThemGioHang, txtYeuThich, txtMuaNgay;
    RecyclerView rcvSanPhamLQ;
    List<DoAn> listDoAn;
    DoAnAdapter doAnAdapter;
    DoAn doAnChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_do_an);

        imgAnhDoAn = findViewById(R.id.image_anhdoan);
        txtTenDoAn = findViewById(R.id.text_tenmonan);
        txtGia = findViewById(R.id.text_giamonan);
        txtDiaChi = findViewById(R.id.text_diachimonan);
        txtTrangThai = findViewById(R.id.text_tinhtrang);
        rcvSanPhamLQ = findViewById(R.id.recycle_sanphamlienquan);
        imgTroVeCT = findViewById(R.id.chitiedoan_trove);
        imgTimKiemCT = findViewById(R.id.imageview_timkiem_ct);
        txtThemGioHang = findViewById(R.id.chitiet_themgiohang);
        txtYeuThich = findViewById(R.id.chitiet_yeuthich);
        txtMuaNgay = findViewById(R.id.chitiet_muangay);


        imgTimKiemCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietDoAnActivity.this, TimKiemActivity.class));
            }
        });

        imgTroVeCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietDoAnActivity.this, MainActivity2.class));
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        doAnChiTiet = (DoAn) bundle.get("doanchitiet");

        new DownloadImageTask(imgAnhDoAn).execute(doAnChiTiet.getImageDoAn());
        txtTenDoAn.setText(doAnChiTiet.getTenDoAn());
        txtGia.setText("Giá: " + doAnChiTiet.getGia());
        txtDiaChi.setText("Địa chỉ: " + doAnChiTiet.getDiaChi());
        txtTrangThai.setText("Tình trạng: " + doAnChiTiet.getTinhTrang());

        if(doAnChiTiet.isYeuThich()){
            txtYeuThich.setBackground(getDrawable(R.drawable.mau_nen));
        }

        if(doAnChiTiet.isGioHang()){
            txtThemGioHang.setBackground(getDrawable(R.drawable.mau_nen));
        }

        txtThemGioHang.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(doAnChiTiet.isGioHang()){
                    txtThemGioHang.setBackground(getDrawable(R.drawable.mau_nen_trang));
                    UpdateDoAnYTCT(doAnChiTiet.isYeuThich(), false, doAnChiTiet);
                } else {
                    txtThemGioHang.setBackground(getDrawable(R.drawable.mau_nen));
                    UpdateDoAnYTCT(doAnChiTiet.isYeuThich(), true, doAnChiTiet);
                }
            }
        });

        txtYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doAnChiTiet.isYeuThich()){
                    txtYeuThich.setBackground(getDrawable(R.drawable.mau_nen_trang));
                    UpdateDoAnYTCT(false, doAnChiTiet.isGioHang(), doAnChiTiet);
                } else {
                    txtYeuThich.setBackground(getDrawable(R.drawable.mau_nen));
                    UpdateDoAnYTCT(true, doAnChiTiet.isGioHang(), doAnChiTiet);
                }
            }
        });

        listDoAn = new ArrayList<>();
        rcvSanPhamLQ.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSanPhamLQ.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvSanPhamLQ.setFocusable(false);
        doAnAdapter = new DoAnAdapter();
        doAnAdapter.setData(getListDoAnCT(), this);
        rcvSanPhamLQ.setAdapter(doAnAdapter);



    }

    private List<DoAn> getListDoAnCT(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("danhsachdoan");
        listDoAn = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    DoAn doAnCT = snapshot1.getValue(DoAn.class);
                    if((doAnCT.getType() == doAnChiTiet.getType()) && (doAnChiTiet.getTenDoAn().equals(doAnCT.getTenDoAn()) == false)){
                        listDoAn.add(doAnCT);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listDoAn;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public void UpdateDoAnYTCT(boolean giaTri1, boolean giaTri2, DoAn doAn){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("danhsachdoan");
        doAn.setYeuThich(giaTri1);
        doAn.setGioHang(giaTri2);
        databaseReference.child(String.valueOf(doAn.getId())).updateChildren(doAn.mapYeuThich());
    }
}