// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FriendProfileActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.FriendProfileActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492995, "field 'last_name_text_view'");
    target.last_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131492993, "field 'pic_image_view'");
    target.pic_image_view = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131492991, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131492994, "field 'first_name_text_view'");
    target.first_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131492992, "field 'unique_id_text_view'");
    target.unique_id_text_view = (android.widget.TextView) view;
  }

  public static void reset(com.example.plan8_ui.Friends.FriendProfileActivity target) {
    target.last_name_text_view = null;
    target.pic_image_view = null;
    target.toolbar = null;
    target.first_name_text_view = null;
    target.unique_id_text_view = null;
  }
}
