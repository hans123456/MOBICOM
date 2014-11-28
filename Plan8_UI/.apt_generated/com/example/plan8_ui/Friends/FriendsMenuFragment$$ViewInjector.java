// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FriendsMenuFragment$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.FriendsMenuFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493029, "field 'tabLayout'");
    target.tabLayout = (com.example.android.common.view.SlidingTabLayout) view;
    view = finder.findRequiredView(source, 2131493030, "field 'friends_view_pager'");
    target.friends_view_pager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.example.plan8_ui.Friends.FriendsMenuFragment target) {
    target.tabLayout = null;
    target.friends_view_pager = null;
  }
}
