package com.github.natros.dagger.assisted.demo;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = MyModule.class)
public interface MyComponent {
  App app();
}
