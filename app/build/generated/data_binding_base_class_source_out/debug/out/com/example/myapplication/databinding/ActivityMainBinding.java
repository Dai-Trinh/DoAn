// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonDanhnhap;

  @NonNull
  public final CheckBox checkDangnhap;

  @NonNull
  public final EditText editMatkhau1;

  @NonNull
  public final EditText editTendangnhap1;

  @NonNull
  public final TextView textDangky;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull Button buttonDanhnhap,
      @NonNull CheckBox checkDangnhap, @NonNull EditText editMatkhau1,
      @NonNull EditText editTendangnhap1, @NonNull TextView textDangky) {
    this.rootView = rootView;
    this.buttonDanhnhap = buttonDanhnhap;
    this.checkDangnhap = checkDangnhap;
    this.editMatkhau1 = editMatkhau1;
    this.editTendangnhap1 = editTendangnhap1;
    this.textDangky = textDangky;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_danhnhap;
      Button buttonDanhnhap = ViewBindings.findChildViewById(rootView, id);
      if (buttonDanhnhap == null) {
        break missingId;
      }

      id = R.id.check_dangnhap;
      CheckBox checkDangnhap = ViewBindings.findChildViewById(rootView, id);
      if (checkDangnhap == null) {
        break missingId;
      }

      id = R.id.edit_matkhau1;
      EditText editMatkhau1 = ViewBindings.findChildViewById(rootView, id);
      if (editMatkhau1 == null) {
        break missingId;
      }

      id = R.id.edit_tendangnhap1;
      EditText editTendangnhap1 = ViewBindings.findChildViewById(rootView, id);
      if (editTendangnhap1 == null) {
        break missingId;
      }

      id = R.id.text_dangky;
      TextView textDangky = ViewBindings.findChildViewById(rootView, id);
      if (textDangky == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, buttonDanhnhap, checkDangnhap,
          editMatkhau1, editTendangnhap1, textDangky);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
