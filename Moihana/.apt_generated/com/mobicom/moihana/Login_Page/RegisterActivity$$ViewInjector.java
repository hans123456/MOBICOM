// Generated code from Butter Knife. Do not modify!
package com.mobicom.moihana.Login_Page;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RegisterActivity$$ViewInjector {
  public static void inject(Finder finder, final com.mobicom.moihana.Login_Page.RegisterActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493025, "field 'confirm_password_edit_text'");
    target.confirm_password_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493020, "field 'username_edit_text'");
    target.username_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493023, "field 'email_edit_text'");
    target.email_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493021, "field 'first_name_edit_text'");
    target.first_name_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493024, "field 'password_edit_text'");
    target.password_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493022, "field 'last_name_edit_text'");
    target.last_name_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493019, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
  }

  public static void reset(com.mobicom.moihana.Login_Page.RegisterActivity target) {
    target.confirm_password_edit_text = null;
    target.username_edit_text = null;
    target.email_edit_text = null;
    target.first_name_edit_text = null;
    target.password_edit_text = null;
    target.last_name_edit_text = null;
    target.toolbar = null;
  }
}
