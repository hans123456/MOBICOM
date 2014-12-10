// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EventAttendeesActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Friends.EventAttendeesActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492978, "field 'event_attendees_listview' and method 'onItemClick'");
    target.event_attendees_listview = (android.widget.ListView) view;
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onItemClick(p0, p1, p2, p3);
        }
      });
    view = finder.findRequiredView(source, 2131492960, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
  }

  public static void reset(com.example.plan8_ui.Friends.EventAttendeesActivity target) {
    target.event_attendees_listview = null;
    target.toolbar = null;
  }
}
