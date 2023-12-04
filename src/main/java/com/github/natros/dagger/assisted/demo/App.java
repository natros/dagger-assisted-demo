package com.github.natros.dagger.assisted.demo;

import javax.inject.Inject;

public class App {
  private final DialogBox.Factory dialogBoxFactory;

  @Inject
  App(DialogBox.Factory dialogBoxFactory) {
    this.dialogBoxFactory = dialogBoxFactory;
  }

  public static void main(String[] args) {
    DaggerMyComponent.create().app().run();
  }

  private void run() {
    DialogBox dlg1 = dialogBoxFactory.create("dlg1");
    DialogBox dlg2 = dialogBoxFactory.create("dlg2");

    dlg1.show();
    dlg2.show();
  }
}
