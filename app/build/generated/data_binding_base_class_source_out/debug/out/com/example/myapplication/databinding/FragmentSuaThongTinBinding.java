// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSuaThongTinBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonCapnhatsua;

  @NonNull
  public final Button buttonHuysua;

  @NonNull
  public final EditText editEmailsua;

  @NonNull
  public final EditText editSodienthoaisua;

  @NonNull
  public final EditText editTenhienthi;

  @NonNull
  public final LinearLayout layoutThongtin;

  private FragmentSuaThongTinBinding(@NonNull LinearLayout rootView,
      @NonNull Button buttonCapnhatsua, @NonNull Button buttonHuysua,
      @NonNull EditText editEmailsua, @NonNull EditText editSodienthoaisua,
      @NonNull EditText editTenhienthi, @NonNull LinearLayout layoutThongtin) {
    this.rootView = rootView;
    this.buttonCapnhatsua = buttonCapnhatsua;
    this.buttonHuysua = buttonHuysua;
    this.editEmailsua = editEmailsua;
    this.editSodienthoaisua = editSodienthoaisua;
    this.editTenhienthi = editTenhienthi;
    this.layoutThongtin = layoutThongtin;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSuaThongTinBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSuaThongTinBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_sua_thong_tin, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSuaThongTinBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_capnhatsua;
      Button buttonCapnhatsua = ViewBindings.findChildViewById(rootView, id);
      if (buttonCapnhatsua == null) {
        break missingId;
      }

      id = R.id.button_huysua;
      Button buttonHuysua = ViewBindings.findChildViewById(rootView, id);
      if (buttonHuysua == null) {
        break missingId;
      }

      id = R.id.edit_emailsua;
      EditText editEmailsua = ViewBindings.findChildViewById(rootView, id);
      if (editEmailsua == null) {
        break missingId;
      }

      id = R.id.edit_sodienthoaisua;
      EditText editSodienthoaisua = ViewBindings.findChildViewById(rootView, id);
      if (editSodienthoaisua == null) {
        break missingId;
      }

      id = R.id.edit_tenhienthi;
      EditText editTenhienthi = ViewBindings.findChildViewById(rootView, id);
      if (editTenhienthi == null) {
        break missingId;
      }

      id = R.id.layout_thongtin;
      LinearLayout layoutThongtin = ViewBindings.findChildViewById(rootView, id);
      if (layoutThongtin == null) {
        break missingId;
      }

      return new FragmentSuaThongTinBinding((LinearLayout) rootView, buttonCapnhatsua, buttonHuysua,
          editEmailsua, editSodienthoaisua, editTenhienthi, layoutThongtin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}