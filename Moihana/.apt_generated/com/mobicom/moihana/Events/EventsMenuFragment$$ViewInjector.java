// Generated code from Butter Knife. Do not modify!
package com.mobicom.moihana.Events;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EventsMenuFragment$$ViewInjector {
  public static void inject(Finder finder, final com.mobicom.moihana.Events.EventsMenuFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493042, "field 'tabLayout'");
    target.tabLayout = (com.example.android.common.view.SlidingTabLayout) view;
    view = finder.findRequiredView(source, 2131493043, "field 'events_view_pager'");
    target.events_view_pager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.mobicom.moihana.Events.EventsMenuFragment target) {
    target.tabLayout = null;
    target.events_view_pager = null;
  }
}
