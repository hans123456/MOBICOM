// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SendFriendRequestActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.SendFriendRequestActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493024, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131493025, "field 'unique_id_edit_text'");
    target.unique_id_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493028, "field 'last_name_text_view'");
    target.last_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493027, "field 'first_name_text_view'");
    target.first_name_text_view = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493029, "field 'fab' and method 'onClickSearch'");
    target.fab = (com.melnykov.fab.FloatingActionButton) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickSearch();
        }
      });
    view = finder.findRequiredView(source, 2131493026, "field 'pic_image_view'");
    target.pic_image_view = (android.widget.ImageView) view;
  }

  public static void reset(com.example.plan8_ui.Friends.SendFriendRequestActivity target) {
    target.toolbar = null;
    target.unique_id_edit_text = null;
    target.last_name_text_view = null;
    target.first_name_text_view = null;
    target.fab = null;
    target.pic_image_view = null;
  }
}
