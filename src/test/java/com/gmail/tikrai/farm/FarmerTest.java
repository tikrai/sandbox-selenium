package com.gmail.tikrai.farm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class FarmerTest {
  private final Vegetable carrot = new Vegetable(1, "carrot", 10);
  private final Vegetable potato = new Vegetable(2, "potato", 10);

  private final Fertilizer shit = new Fertilizer(1, "shit", true);
  private final Fertilizer ammonia = new Fertilizer(2, "ammonia", false);

  private final Farmer oldFarmer = new Farmer(
      1,
      "lic",
      "first",
      "last",
      new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("06/24/1960"),
      Collections.singletonList(carrot),
      Collections.singletonList(shit)
  );
  private final Farmer youngFarmer = new Farmer(
      1,
      "lic",
      "name",
      "last",
      new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("06/24/2010"),
      Collections.singletonList(potato),
      Collections.singletonList(shit)
  );
  private final Farmer chemicOldFarmer = new Farmer(
      1,
      "lic",
      "name",
      "last",
      new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("06/24/1960"),
      Collections.singletonList(potato),
      Collections.singletonList(ammonia)
  );

  FarmerTest() throws ParseException {
  }

  @Test
  void shouldVerifyFarmerIsOld() {
    assertThat(oldFarmer.isOlderThan(50), equalTo(true));
  }

  @Test
  void shouldVerifyFarmerIsYoung() {
    assertThat(youngFarmer.isOlderThan(50), equalTo(false));
  }

  @Test
  void shouldReturnGoodVegetables() {
    List<Vegetable> vegetables =
        Farmer.goodVegetables(Arrays.asList(oldFarmer, youngFarmer, chemicOldFarmer));

    assertThat(vegetables, equalTo(Collections.singletonList(carrot)));
  }
}
