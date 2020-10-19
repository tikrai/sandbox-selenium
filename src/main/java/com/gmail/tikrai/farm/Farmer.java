package com.gmail.tikrai.farm;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Farmer {
  int id;
  String license;
  String firstName;
  String lastName;
  Date birthday;
  List<Vegetable> vegetables;
  List<Fertilizer> fertilizers;

  public Farmer(int id,
      String license,
      String firstName,
      String lastName,
      Date birthday,
      List<Vegetable> vegetables,
      List<Fertilizer> fertilizers
  ) {
    this.id = id;
    this.license = license;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.vegetables = vegetables;
    this.fertilizers = fertilizers;
  }

  public boolean isOlderThan(int years) {
    return new Date().toInstant()
        .minus(ChronoUnit.YEARS.getDuration().multipliedBy(years))
        .isAfter(birthday.toInstant());
  }

  public boolean isOlderThan50() {
    return isOlderThan(50);
  }

  public boolean usesOrganicFertilizersOnly() {
    return fertilizers.stream().allMatch(Fertilizer::isOrganic);
  }

  public static List<Vegetable> goodVegetables(List<Farmer> farmers) {
    return farmers.stream()
        .filter(Farmer::isOlderThan50)
        .filter(Farmer::usesOrganicFertilizersOnly)
        .map(Farmer::getVegetables)
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }

  public int getId() {
    return id;
  }

  public String getLicense() {
    return license;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Date getBirthday() {
    return birthday;
  }

  public List<Vegetable> getVegetables() {
    return vegetables;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }
}
