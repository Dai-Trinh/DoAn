// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChiTietDoAnBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView chitiedoanTrove;

  @NonNull
  public final TextView chitietMuangay;

  @NonNull
  public final TextView chitietThemgiohang;

  @NonNull
  public final TextView chitietYeuthich;

  @NonNull
  public final ImageView imageAnhdoan;

  @NonNull
  public final ImageView imageviewTimkiemCt;

  @NonNull
  public final RecyclerView recycleSanphamlienquan;

  @NonNull
  public final TextView textDiachimonan;

  @NonNull
  public final TextView textGiamonan;

  @NonNull
  public final TextView textTenmonan;

  @NonNull
  public final TextView textTinhtrang;

  private ActivityChiTietDoAnBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView chitiedoanTrove, @NonNull TextView chitietMuangay,
      @NonNull TextView chitietThemgiohang, @NonNull TextView chitietYeuthich,
      @NonNull ImageView imageAnhdoan, @NonNull ImageView imageviewTimkiemCt,
      @NonNull RecyclerView recycleSanphamlienquan, @NonNull TextView textDiachimonan,
      @NonNull TextView textGiamonan, @NonNull TextView textTenmonan,
      @NonNull TextView textTinhtrang) {
    this.rootView = rootView;
    this.chitiedoanTrove = chitiedoanTrove;
    this.chitietMuangay = chitietMuangay;
    this.chitietThemgiohang = chitietThemgiohang;
    this.chitietYeuthich = chitietYeuthich;
    this.imageAnhdoan = imageAnhdoan;
    this.imageviewTimkiemCt = imageviewTimkiemCt;
    this.recycleSanphamlienquan = recycleSanphamlienquan;
    this.textDiachimonan = textDiachimonan;
    this.textGiamonan = textGiamonan;
    this.textTenmonan = textTenmonan;
    this.textTinhtrang = textTinhtrang;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChiTietDoAnBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChiTietDoAnBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_chi_tiet_do_an, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChiTietDoAnBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.chitiedoan_trove;
      ImageView chitiedoanTrove = ViewBindings.findChildViewById(rootView, id);
      if (chitiedoanTrove == null) {
        break missingId;
      }

      id = R.id.chitiet_muangay;
      TextView chitietMuangay = ViewBindings.findChildViewById(rootView, id);
      if (chitietMuangay == null) {
        break missingId;
      }

      id = R.id.chitiet_themgiohang;
      TextView chitietThemgiohang = ViewBindings.findChildViewById(rootView, id);
      if (chitietThemgiohang == null) {
        break missingId;
      }

      id = R.id.chitiet_yeuthich;
      TextView chitietYeuthich = ViewBindings.findChildViewById(rootView, id);
      if (chitietYeuthich == null) {
        break missingId;
      }

      id = R.id.image_anhdoan;
      ImageView imageAnhdoan = ViewBindings.findChildViewById(rootView, id);
      if (imageAnhdoan == null) {
        break missingId;
      }

      id = R.id.imageview_timkiem_ct;
      ImageView imageviewTimkiemCt = ViewBindings.findChildViewById(rootView, id);
      if (imageviewTimkiemCt == null) {
        break missingId;
      }

      id = R.id.recycle_sanphamlienquan;
      RecyclerView recycleSanphamlienquan = ViewBindings.findChildViewById(rootView, id);
      if (recycleSanphamlienquan == null) {
        break missingId;
      }

      id = R.id.text_diachimonan;
      TextView textDiachimonan = ViewBindings.findChildViewById(rootView, id);
      if (textDiachimonan == null) {
        break missingId;
      }

      id = R.id.text_giamonan;
      TextView textGiamonan = ViewBindings.findChildViewById(rootView, id);
      if (textGiamonan == null) {
        break missingId;
      }

      id = R.id.text_tenmonan;
      TextView textTenmonan = ViewBindings.findChildViewById(rootView, id);
      if (textTenmonan == null) {
        break missingId;
      }

      id = R.id.text_tinhtrang;
      TextView textTinhtrang = ViewBindings.findChildViewById(rootView, id);
      if (textTinhtrang == null) {
        break missingId;
      }

      return new ActivityChiTietDoAnBinding((LinearLayout) rootView, chitiedoanTrove,
          chitietMuangay, chitietThemgiohang, chitietYeuthich, imageAnhdoan, imageviewTimkiemCt,
          recycleSanphamlienquan, textDiachimonan, textGiamonan, textTenmonan, textTinhtrang);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
