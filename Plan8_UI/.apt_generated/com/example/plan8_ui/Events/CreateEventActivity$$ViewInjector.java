// Generated code from Butter Knife. Do not modify!
package com.example.plan8_ui.Events;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CreateEventActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.plan8_ui.Events.CreateEventActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492964, "field 'inviteButton' and method 'onClickInvite'");
    target.inviteButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickInvite(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492977, "field 'description_edit_text'");
    target.description_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492973, "field 'time_end_edit_text' and method 'onClickTimeEnd'");
    target.time_end_edit_text = (android.widget.EditText) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickTimeEnd(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492969, "field 'date_start_edit_text' and method 'onClickDateStart'");
    target.date_start_edit_text = (android.widget.EditText) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickDateStart(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492962, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131492975, "field 'location_edit_text'");
    target.location_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492972, "field 'date_end_edit_text' and method 'onClickDateEnd'");
    target.date_end_edit_text = (android.widget.EditText) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickDateEnd(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492967, "field 'title_edit_text'");
    target.title_edit_text = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492970, "field 'time_start_edit_text' and method 'onClickTimeStart'");
    target.time_start_edit_text = (android.widget.EditText) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickTimeStart(p0);
        }
      });
  }

  public static void reset(com.example.plan8_ui.Events.CreateEventActivity target) {
    target.inviteButton = null;
    target.description_edit_text = null;
    target.time_end_edit_text = null;
    target.date_start_edit_text = null;
    target.toolbar = null;
    target.location_edit_text = null;
    target.date_end_edit_text = null;
    target.title_edit_text = null;
    target.time_start_edit_text = null;
  }
}
