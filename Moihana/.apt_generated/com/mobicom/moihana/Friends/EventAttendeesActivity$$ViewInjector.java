// Generated code from Butter Knife. Do not modify!
package com.mobicom.moihana.Friends;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EventAttendeesActivity$$ViewInjector {
  public static void inject(Finder finder, final com.mobicom.moihana.Friends.EventAttendeesActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492978, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131492979, "field 'event_attendees_listview' and method 'onItemClick'");
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
    view = finder.findRequiredView(source, 2131492980, "method 'onClickSendLocation' and method 'onLongClickSendLocation'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickSendLocation(p0);
        }
      });
    view.setOnLongClickListener(
      new android.view.View.OnLongClickListener() {
        @Override public boolean onLongClick(
          android.view.View p0
        ) {
          return target.onLongClickSendLocation(p0);
        }
      });
  }

  public static void reset(com.mobicom.moihana.Friends.EventAttendeesActivity target) {
    target.toolbar = null;
    target.event_attendees_listview = null;
  }
}
