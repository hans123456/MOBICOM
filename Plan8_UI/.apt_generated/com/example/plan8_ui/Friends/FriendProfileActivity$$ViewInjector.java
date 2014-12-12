// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FriendProfileActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.FriendProfileActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493001, "field 'last_name_text_view'");
    target.last_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131492998, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131493002, "field 'fab' and method 'onClickFAB'");
    target.fab = (com.melnykov.fab.FloatingActionButton) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickFAB();
        }
      });
    view = finder.findRequiredView(source, 2131492999, "field 'pic_image_view'");
    target.pic_image_view = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131493000, "field 'first_name_text_view'");
    target.first_name_text_view = (android.widget.TextView) view;
  }

  public static void reset(com.example.plan8_ui.Friends.FriendProfileActivity target) {
    target.last_name_text_view = null;
    target.toolbar = null;
    target.fab = null;
    target.pic_image_view = null;
    target.first_name_text_view = null;
  }
}
