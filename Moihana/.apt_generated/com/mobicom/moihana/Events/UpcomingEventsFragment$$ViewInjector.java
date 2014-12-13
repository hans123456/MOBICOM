// Generated code from Butter Knife. Do not modify!
package com.mobicom.moihana.Events;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class UpcomingEventsFragment$$ViewInjector {
  public static void inject(Finder finder, final com.mobicom.moihana.Events.UpcomingEventsFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493058, "field 'create_button', method 'onClickCreateEvent', and method 'onLongClickCreateEvent'");
    target.create_button = (com.melnykov.fab.FloatingActionButton) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickCreateEvent();
        }
      });
    view.setOnLongClickListener(
      new android.view.View.OnLongClickListener() {
        @Override public boolean onLongClick(
          android.view.View p0
        ) {
          return target.onLongClickCreateEvent();
        }
      });
    view = finder.findRequiredView(source, 2131493057, "field 'events_list_view' and method 'onItemClick'");
    target.events_list_view = (android.widget.ListView) view;
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

  public static void reset(com.mobicom.moihana.Events.UpcomingEventsFragment target) {
    target.create_button = null;
    target.events_list_view = null;
  }
}
