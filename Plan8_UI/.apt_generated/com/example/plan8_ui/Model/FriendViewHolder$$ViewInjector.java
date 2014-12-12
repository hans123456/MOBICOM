// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Model;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FriendViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Model.FriendViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493065, "field 'last_name_text_view'");
    target.last_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493063, "field 'pic_image_view'");
    target.pic_image_view = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131493064, "field 'first_name_text_view'");
    target.first_name_text_view = (android.widget.TextView) view;
  }

  public static void reset(com.example.plan8_ui.Model.FriendViewHolder target) {
    target.last_name_text_view = null;
    target.pic_image_view = null;
    target.first_name_text_view = null;
  }
}
