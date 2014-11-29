// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Events;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EventProfileActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Events.EventProfileActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492984, "field 'attendees_button' and method 'onClickAttendees'");
    target.attendees_button = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickAttendees(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492980, "field 'time_end_edit_text'");
    target.time_end_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492981, "field 'location_edit_text'");
    target.location_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492974, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131492977, "field 'date_start_edit_text'");
    target.date_start_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492978, "field 'time_start_edit_text'");
    target.time_start_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492982, "field 'description_edit_text'");
    target.description_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492983, "field 'invite_button' and method 'onClickInvite'");
    target.invite_button = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickInvite(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492979, "field 'date_end_edit_text'");
    target.date_end_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492976, "field 'title_edit_text'");
    target.title_edit_text = (android.widget.EditText) view;
  }

  public static void reset(com.example.plan8_ui.Events.EventProfileActivity target) {
    target.attendees_button = null;
    target.time_end_edit_text = null;
    target.location_edit_text = null;
    target.toolbar = null;
    target.date_start_edit_text = null;
    target.time_start_edit_text = null;
    target.description_edit_text = null;
    target.invite_button = null;
    target.date_end_edit_text = null;
    target.title_edit_text = null;
  }
}
