// Generated code from Butter Knife. Do not modify!
package com.mobicom.moihana.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FriendsMenuFragment$$ViewInjector {
  public static void inject(Finder finder, final com.mobicom.moihana.Friends.FriendsMenuFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493049, "field 'friends_view_pager'");
    target.friends_view_pager = (android.support.v4.view.ViewPager) view;
    view = finder.findRequiredView(source, 2131493048, "field 'tabLayout'");
    target.tabLayout = (com.example.android.common.view.SlidingTabLayout) view;
  }

  public static void reset(com.mobicom.moihana.Friends.FriendsMenuFragment target) {
    target.friends_view_pager = null;
    target.tabLayout = null;
  }
}
