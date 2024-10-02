package victor.training.stream.dish;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static victor.training.stream.dish.Dish.Type.*;

public class DishExerciseTest {
  static final Dish pork = DishExercise.menu.get(0);
  static final Dish beef = DishExercise.menu.get(1);
  static final Dish chicken = DishExercise.menu.get(2);
  static final Dish frenchFries = DishExercise.menu.get(3);
  static final Dish rice = DishExercise.menu.get(4);
  static final Dish seasonFruit = DishExercise.menu.get(5);
  static final Dish pizza = DishExercise.menu.get(6);
  static final Dish prawns = DishExercise.menu.get(7);
  static final Dish salmon = DishExercise.menu.get(8);

  @Nested
  class FilterTests {
    @Test
    void lowCalories() {
      assertThat(DishExercise.lowCalories())
          .containsExactlyInAnyOrder(rice, seasonFruit, prawns);
    }

    @Test
    void highCalorie() {
      assertThat(DishExercise.highCalorie())
          .containsExactlyInAnyOrder(pork, beef, frenchFries, pizza, salmon);
    }

    @Test
    void vegetarian() {
      assertThat(DishExercise.vegetarian())
          .containsExactlyInAnyOrder(frenchFries, rice, seasonFruit);
    }

    @Test
    void nonFish() {
      assertThat(DishExercise.nonFish())
          .containsExactlyInAnyOrder(pork, beef, chicken, frenchFries, rice, seasonFruit, pizza);
    }

    @Test
    void multiWord() {
      assertThat(DishExercise.multiWord())
          .containsExactlyInAnyOrder(frenchFries, seasonFruit);
    }
  }

  @Nested
  class MapTests {
    @Test
    void names() {
      assertThat(DishExercise.names())
          .containsExactlyInAnyOrder("pork", "beef", "chicken", "french fries", "rice", "season fruit", "pizza", "prawns", "salmon");
    }

    @Test
    void types() {
      assertThat(DishExercise.types())
          .containsAll(List.of(MEAT, OTHER, FISH));
    }

    @Test
    void namesOfVegetarianDishes() {
      assertThat(DishExercise.namesOfVegetarianDishes())
          .containsExactlyInAnyOrder("french fries", "rice", "season fruit");
    }

    @Test
    void namesOfLowCalories() {
      assertThat(DishExercise.namesOfLowCalories())
          .containsExactlyInAnyOrder("rice", "season fruit", "prawns");
    }

    @Test
    void namesInUpper() {
      assertThat(DishExercise.namesInUpper())
          .containsExactlyInAnyOrder("PORK", "BEEF", "CHICKEN", "FRENCH FRIES", "RICE", "SEASON FRUIT", "PIZZA", "PRAWNS", "SALMON");
    }

    @Test
    void newDishesWithNameUpperCase() {
      assertThat(DishExercise.newDishesWithNameUpperCase())
          .extracting(Dish::getName)
          .containsExactlyInAnyOrder("PORK", "BEEF", "CHICKEN", "FRENCH FRIES", "RICE", "SEASON FRUIT", "PIZZA", "PRAWNS", "SALMON");
    }

    @Test
    void allTypesDistinct() {
      assertThat(DishExercise.allTypesDistinct())
          .containsExactlyInAnyOrder(MEAT, OTHER, FISH);
    }

    @Test
    void allTypesAsSet() {
      assertThat(DishExercise.allTypesAsSet())
          .containsExactlyInAnyOrder(MEAT, OTHER, FISH);
    }
  }

  @Nested
  class MatchTests {
    @Test
    void anyVegetarian() {
      assertThat(new DishExercise().anyVegetarian()).isTrue();
    }

    @Test
    void noneFish() {
      assertThat(new DishExercise().noneFish()).isTrue();
    }

    @Test
    void vegetarianArentMeat() {
      assertThat(new DishExercise().vegetarianArentMeat()).isTrue();
    }

    @Test
    void allAreUnder1000cal() {
      assertThat(new DishExercise().allAreUnder1000cal()).isTrue();
    }

    @Test
    void kForMeat() {
      assertThat(new DishExercise().kForMeat()).isTrue();
    }
  }

  @Nested
  class FindFirstTests {
    @Test
    void lowCaloriesMeat() {
      assertThat(DishExercise.lowCaloriesMeat()).isEqualTo(chicken);
    }

    @Test
    void fries() {
      assertThat(DishExercise.fries()).isEqualTo(frenchFries);
    }

    @Test
    void pizzaCalories() {
      assertThat(new DishExercise().pizzaCalories()).isEqualTo(550);
    }
  }

  @Nested
  class SortingTests {
    @Test
    void sortedByName() {
      assertThat(DishExercise.sortedByName())
          .containsExactlyInAnyOrder(pork, beef, chicken, frenchFries, rice, seasonFruit, pizza, prawns, salmon);
    }

    @Test
    void sortedByCalories() {
      assertThat(DishExercise.sortedByCalories())
          .containsExactlyInAnyOrder(rice, seasonFruit, prawns, chicken, salmon, pizza, frenchFries, beef, pork);
    }

    @Test
    void sortedByCaloriesDescending() {
      assertThat(DishExercise.sortedByCaloriesDescending())
          .containsExactlyInAnyOrder(pork, beef, frenchFries, pizza, salmon, chicken, prawns, rice, seasonFruit);
    }

    @Test
    void sortedByTypeThenByName() {
      assertThat(DishExercise.sortedByTypeThenByName())
          .containsExactlyInAnyOrder(beef, chicken, pork, salmon, prawns, frenchFries, pizza, rice, seasonFruit);
    }

    @Test
    void maxCaloric() {
      assertThat(DishExercise.maxCaloric()).isEqualTo(pork);
    }

    @Test
    void top3Caloric() {
      assertThat(DishExercise.top3Caloric())
          .containsExactlyInAnyOrder(pork, beef, pizza);
    }

    @Test
    void secondMostCaloric() {
      assertThat(DishExercise.secondMostCaloric()).isEqualTo(beef);
    }

    @Test
    void secondAndThirdMostCaloric() {
      assertThat(DishExercise.secondAndThirdMostCaloric())
          .containsExactlyInAnyOrder(beef, pizza);
    }

    @Test
    void higherVegetarianCalories() {
      assertThat(new DishExercise().higherVegetarianCalories()).isEqualTo("french fries");
    }

    @Test
    void meatOptions() {
      assertThat(DishExercise.meatOptions())
          .containsExactlyInAnyOrder(pork, beef);
    }
  }

  @Nested
  class CollectTests {
    @Test
    void namesCommaSeparated() {
      assertThat(DishExercise.namesCommaSeparated())
          .isEqualTo("beef,chicken,french fries,pizza,pork,prawns,rice,salmon,season fruit");
    }

    @Test
    void toMenuString() {
      assertThat(DishExercise.toMenuString())
          .isEqualTo("pork (800 cal)\nbeef (700 cal)\nchicken (400 cal)\nfrench fries (530 cal), veg\nrice (350 cal), veg\nseason fruit (120 cal), veg\npizza (550 cal)\nprawns (300 cal)\nsalmon (450 cal)");
    }

    @Test
    void toIstambulMenuString() {
      assertThat(DishExercise.toIstambulMenuString())
          .isEqualTo("beef (700 cal)\nchicken (400 cal)\nfrench fries (530 cal), veg\nrice (350 cal), veg\nseason fruit (120 cal), veg\npizza (550 cal)\nprawns (300 cal)\nsalmon (450 cal)");
    }
  }

  @Nested
  class SumAndAverageTests {
    @Test
    void totalCalories() {
      assertThat(DishExercise.totalCalories()).isEqualTo(4200);
    }

    @Test
    void averageMeatCalories() {
      assertThat(DishExercise.averageMeatCalories()).isEqualTo(466.67, Offset.offset(0.01));
    }

    @Test
    void averageFishCalories() {
      assertThat(DishExercise.averageFishCalories()).isEqualTo(375.0);
    }

    @Test
    void charactersInTheNames() {
      assertThat(DishExercise.charactersInTheNames()).isEqualTo(60);
    }

    @Test
    void totalPriceForFish() {
      Map<Dish, BigDecimal> pricePerDish = Map.of(
          DishExercise.menu.get(7), new BigDecimal("12.50"),
          DishExercise.menu.get(8), new BigDecimal("15.00")
      );
      assertThat(DishExercise.totalPriceForFish(pricePerDish)).isEqualTo(new BigDecimal("27.50"));
    }
  }
}