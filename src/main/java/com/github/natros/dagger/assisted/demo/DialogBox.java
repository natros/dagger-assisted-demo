package com.github.natros.dagger.assisted.demo;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;

public class DialogBox {
  private final Messages messages;

  @AssistedInject
  DialogBox(Messages messages, @Assisted String caption) {
    this.messages = messages;
    messages.log(String.format("creating DialogBox(%s)", caption));
  }

  public void show() {
    messages.log("show dialog");
  }

  @AssistedFactory
  public interface Factory {
    DialogBox create(String caption);
  }
}
