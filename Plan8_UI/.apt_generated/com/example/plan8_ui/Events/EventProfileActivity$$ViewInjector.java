// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Events;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EventProfileActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Events.EventProfileActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492977, "field 'start_date_et'");
    target.start_date_et = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492979, "field 'end_date_et'");
    target.end_date_et = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492976, "field 'title_et'");
    target.title_et = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492980, "field 'end_time_et'");
    target.end_time_et = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492982, "field 'description_et'");
    target.description_et = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492984, "field 'attendeesButton' and method 'onClickAttendees'");
    target.attendeesButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickAttendees(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492981, "field 'location_et'");
    target.location_et = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492978, "field 'start_time_et'");
    target.start_time_et = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492983, "field 'inviteButton' and method 'onClickInvite'");
    target.inviteButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickInvite(p0);
        }
      });
  }

  public static void reset(com.example.plan8_ui.Events.EventProfileActivity target) {
    target.start_date_et = null;
    target.end_date_et = null;
    target.title_et = null;
    target.end_time_et = null;
    target.description_et = null;
    target.attendeesButton = null;
    target.location_et = null;
    target.start_time_et = null;
    target.inviteButton = null;
  }
}
