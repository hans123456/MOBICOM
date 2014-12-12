// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class InviteFriendsActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.InviteFriendsActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493003, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131493004, "field 'friends_list_view' and method 'onItemClickFriends'");
    target.friends_list_view = (android.widget.ListView) view;
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onItemClickFriends(p2);
        }
      });
  }

  public static void reset(com.example.plan8_ui.Friends.InviteFriendsActivity target) {
    target.toolbar = null;
    target.friends_list_view = null;
  }
}
