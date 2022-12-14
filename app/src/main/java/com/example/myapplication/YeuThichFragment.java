package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YeuThichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YeuThichFragment extends Fragment {

    private List<DoAn> listYeuThich;
    private RecyclerView recyclerViewYeuThich;
    private DoAnAdapter yeuThichAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YeuThichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YeuThichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YeuThichFragment newInstance(String param1, String param2) {
        YeuThichFragment fragment = new YeuThichFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yeu_thich, container, false);
        recyclerViewYeuThich = view.findViewById(R.id.recycle_view_yeuthich);
        listYeuThich = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewYeuThich.setLayoutManager(gridLayoutManager);
        yeuThichAdapter = new DoAnAdapter();
        yeuThichAdapter.setData(listYeuThich, view.getContext());
        getListYeuThich();
        recyclerViewYeuThich.setAdapter(yeuThichAdapter);
        return view;
    }

    public void getListYeuThich(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("danhsachdoan");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listYeuThich != null){
                    listYeuThich.clear();
                }
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    DoAn doAn = dataSnapshot.getValue(DoAn.class);
                    if(doAn.isYeuThich()){
                        listYeuThich.add(doAn);
                    }
                }
                yeuThichAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}