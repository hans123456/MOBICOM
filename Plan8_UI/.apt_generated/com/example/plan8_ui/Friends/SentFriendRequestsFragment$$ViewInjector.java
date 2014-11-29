// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SentFriendRequestsFragment$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.SentFriendRequestsFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493046, "field 'sent_frient_requests_list_view' and method 'onItemClick'");
    target.sent_frient_requests_list_view = (android.widget.ListView) view;
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

  public static void reset(com.example.plan8_ui.Friends.SentFriendRequestsFragment target) {
    target.sent_frient_requests_list_view = null;
  }
}
