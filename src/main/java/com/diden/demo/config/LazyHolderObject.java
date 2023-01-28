package com.diden.demo.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class LazyHolderObject {
  public static Gson getGson() {
    return GsonLazyHolder.gson;
  }

  private static final class GsonLazyHolder {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  }
}
