package com.gmail.tikrai.farm;

public class Fertilizer {

  int id;
  String name;
  boolean organic;

  public Fertilizer(int id, String name, boolean organic) {
    this.id = id;
    this.name = name;
    this.organic = organic;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isOrganic() {
    return organic;
  }
}
