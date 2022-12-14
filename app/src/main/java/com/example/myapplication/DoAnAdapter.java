package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DoAnAdapter extends RecyclerView.Adapter<DoAnAdapter.FoodViewHolder> implements Filterable {

    private List<DoAn> mListDoAn;
    private Context context;
    private List<DoAn> mListDoAnOld;

    public void  setData(List<DoAn> list, Context context1){
        this.mListDoAn = list;
        this.context = context1;
        this.mListDoAnOld = list;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_doan, parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        DoAn doan = mListDoAn.get(position);
        if(doan == null){
            return;
        }


        new DownloadImageTask(holder.imgDoAn).execute(doan.getImageDoAn());
        holder.txtTenDoAn.setText(doan.getTenDoAn());

        if(doan.isGioHang()){
            holder.imgGioHang.setImageResource(R.drawable.shopping_cart_black);
        } else {
            holder.imgGioHang.setImageResource(R.drawable.shopping_cart_24);
        }

        if (doan.isYeuThich()){
            holder.imgYeuthich.setImageResource(R.drawable.love_24_red);
        } else {
            holder.imgYeuthich.setImageResource(R.drawable.love_24);
        }

        holder.layoutChiTietDoAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goChiTietDoAn(doan);
            }
        });

//click vào nút yêu thích và thêm vào giỏ hàng
        holder.imgYeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doan.isYeuThich()){
                    holder.imgYeuthich.setImageResource(R.drawable.love_24);
                    UpdateDoAnYT(false, doan.isGioHang(), doan);
                } else {
                    holder.imgYeuthich.setImageResource(R.drawable.love_24_red);
                    UpdateDoAnYT(true, doan.isGioHang(), doan);
                }
            }
        });

        holder.imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doan.isGioHang()){
                    holder.imgGioHang.setImageResource(R.drawable.shopping_cart_24);
                    UpdateDoAnYT(doan.isYeuThich(), false, doan);

                } else {
                    holder.imgGioHang.setImageResource(R.drawable.shopping_cart_black);
                    UpdateDoAnYT(doan.isYeuThich(), true, doan);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListDoAn != null){
            return mListDoAn.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mListDoAn = mListDoAnOld;
                } else {
                    List<DoAn> anList = new ArrayList<>();
                    for (DoAn doAn: mListDoAnOld){
                        if (doAn.getTenDoAn().toLowerCase().contains(strSearch.toLowerCase())){
                            anList.add(doAn);
                        }
                    }
                    mListDoAn = anList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListDoAn;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListDoAn = (List<DoAn>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgDoAn;
        private TextView txtTenDoAn;
        private ImageButton imgGioHang;
        private ImageButton imgYeuthich;
        private LinearLayout layoutChiTietDoAn;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDoAn = itemView.findViewById(R.id.image_item_doan);
            txtTenDoAn = itemView.findViewById(R.id.text_ten_doan);
            imgGioHang = itemView.findViewById(R.id.button_themgiohang);
            imgYeuthich = itemView.findViewById(R.id.button_yeuthich);
            layoutChiTietDoAn = itemView.findViewById(R.id.layout_chitietdoan);
        }
    }


    //chuyển ảnh từ dạng link trên firebase về
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

    //Cập nhật trạng thái yêu thích hoặc giỏ hàng lên firebase
    public void UpdateDoAnYT(boolean giaTri1, boolean giaTri2, DoAn doAn){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("danhsachdoan");
        doAn.setYeuThich(giaTri1);
        doAn.setGioHang(giaTri2);
        databaseReference.child(String.valueOf(doAn.getId())).updateChildren(doAn.mapYeuThich());
    }

    private void goChiTietDoAn(DoAn doAnChiTiet){
        Intent intent = new Intent(context, ChiTietDoAnActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("doanchitiet",doAnChiTiet);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
