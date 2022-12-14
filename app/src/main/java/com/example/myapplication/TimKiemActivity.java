package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    ImageView imgBack, imgTimKiem;
    List<String> tenDoAn;
    List<DoAn> doAnList;
    DoAnAdapter adapterTimKem;
    RecyclerView rcvTimKiem;
    List<DoAn> doTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        textInputLayout = findViewById(R.id.text_inputlayout);
        autoCompleteTextView = findViewById(R.id.product);
        imgBack = findViewById(R.id.imageview_back);
        imgTimKiem = findViewById(R.id.imageview_timkiem_sanpham);
        tenDoAn = new ArrayList<>();
        doAnList = new ArrayList<>();
        rcvTimKiem = findViewById(R.id.recycle_view_doantimkiem);
        doTimKiem = new ArrayList<>();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TimKiemActivity.this, MainActivity2.class));
            }
        });

        LayString();
        autoCompleteTextView = findViewById(R.id.product);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, tenDoAn);
        autoCompleteTextView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvTimKiem.setLayoutManager(gridLayoutManager);
        adapterTimKem = new DoAnAdapter();
        adapterTimKem.setData(doTimKiem, this);
        rcvTimKiem.setAdapter(adapterTimKem);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                doTimKiem.clear();
                String tuKhoa = autoCompleteTextView.getText().toString().trim();
                for (DoAn doAn1: doAnList){
                    if(doAn1.getTenDoAn().equals(tuKhoa)){
                        doTimKiem.add(doAn1);
                    }
                }
                adapterTimKem.notifyDataSetChanged();
            }
        });

//        textInputLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doTimKiem.clear();
//                String tuKhoa = autoCompleteTextView.getText().toString().trim();
//                for (DoAn doAn1: doAnList){
//                    if(doAn1.getTenDoAn().equals(tuKhoa)){
//                        doTimKiem.add(doAn1);
//                    }
//                }
//                adapterTimKem.notifyDataSetChanged();
//            }
//        });

    }

    public void LayString(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("danhsachdoan");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    DoAn doAnTK = data.getValue(DoAn.class);
                    doAnList.add(doAnTK);
                    tenDoAn.add(doAnTK.getTenDoAn());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}