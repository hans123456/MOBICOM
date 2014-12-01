// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Login_Page;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Login_Page.MainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493006, "field 'username_edit_text'");
    target.username_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493007, "field 'password_edit_text'");
    target.password_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493004, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131493008, "method 'onClickLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLogin(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493009, "method 'onClickRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickRegister(p0);
        }
      });
  }

  public static void reset(com.example.plan8_ui.Login_Page.MainActivity target) {
    target.username_edit_text = null;
    target.password_edit_text = null;
    target.toolbar = null;
  }
}
