package com.gmail.tikrai.farm;

public class Vegetable {
  int id;
  String name;
  int ageDays;

  public Vegetable(int id, String name, int ageDays) {
    this.id = id;
    this.name = name;
    this.ageDays = ageDays;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAgeDays() {
    return ageDays;
  }
}
