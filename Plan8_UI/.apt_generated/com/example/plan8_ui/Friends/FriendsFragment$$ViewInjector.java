// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FriendsFragment$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.FriendsFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493040, "field 'addFriends' and method 'onClick'");
    target.addFriends = (com.melnykov.fab.FloatingActionButton) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131493039, "field 'friends_list_view' and method 'onItemClick'");
    target.friends_list_view = (android.widget.ListView) view;
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onItemClick(p2);
        }
      });
  }

  public static void reset(com.example.plan8_ui.Friends.FriendsFragment target) {
    target.addFriends = null;
    target.friends_list_view = null;
  }
}
