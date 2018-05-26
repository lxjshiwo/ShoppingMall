// Generated code from Butter Knife. Do not modify!
package com.example.a8540w.shopppingmall.app;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.a8540w.shopppingmall.app.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230787, "field 'frameLayout'");
    target.frameLayout = finder.castView(view, 2131230787, "field 'frameLayout'");
    view = finder.findRequiredView(source, 2131230847, "field 'rgMain'");
    target.rgMain = finder.castView(view, 2131230847, "field 'rgMain'");
  }

  @Override public void unbind(T target) {
    target.frameLayout = null;
    target.rgMain = null;
  }
}
