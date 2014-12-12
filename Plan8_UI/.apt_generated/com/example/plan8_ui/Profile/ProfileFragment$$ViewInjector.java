// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Profile;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ProfileFragment$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Profile.ProfileFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493053, "field 'last_name_text_view'");
    target.last_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493052, "field 'first_name_text_view'");
    target.first_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493051, "field 'image_view'");
    target.image_view = (android.widget.ImageView) view;
  }

  public static void reset(com.example.plan8_ui.Profile.ProfileFragment target) {
    target.last_name_text_view = null;
    target.first_name_text_view = null;
    target.image_view = null;
  }
}
