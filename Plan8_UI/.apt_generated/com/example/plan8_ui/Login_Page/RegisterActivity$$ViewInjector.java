// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Login_Page;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RegisterActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Login_Page.RegisterActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493008, "field 'username_edit_text'");
    target.username_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493012, "field 'password_edit_text'");
    target.password_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493013, "field 'confirm_password_edit_text'");
    target.confirm_password_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493010, "field 'last_name_edit_text'");
    target.last_name_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493011, "field 'email_edit_text'");
    target.email_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493009, "field 'first_name_edit_text'");
    target.first_name_edit_text = (android.widget.EditText) view;
  }

  public static void reset(com.example.plan8_ui.Login_Page.RegisterActivity target) {
    target.username_edit_text = null;
    target.password_edit_text = null;
    target.confirm_password_edit_text = null;
    target.last_name_edit_text = null;
    target.email_edit_text = null;
    target.first_name_edit_text = null;
  }
}
