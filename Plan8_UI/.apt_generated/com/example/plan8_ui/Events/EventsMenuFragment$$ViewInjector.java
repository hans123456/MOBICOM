// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Events;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EventsMenuFragment$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Events.EventsMenuFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493036, "field 'events_view_pager'");
    target.events_view_pager = (android.support.v4.view.ViewPager) view;
    view = finder.findRequiredView(source, 2131493035, "field 'tabLayout'");
    target.tabLayout = (com.example.android.common.view.SlidingTabLayout) view;
  }

  public static void reset(com.example.plan8_ui.Events.EventsMenuFragment target) {
    target.events_view_pager = null;
    target.tabLayout = null;
  }
}
