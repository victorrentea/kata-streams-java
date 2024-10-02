package victor.training.stream.dish;

import victor.training.stream.dish.Dish.Type;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static victor.training.stream.dish.Dish.Type.*;

public class DishExercise_Solution {
  public static final List<Dish> menu = Arrays.asList(
      new Dish("pork", false, 800, MEAT),
      new Dish("beef", false, 700, MEAT),
      new Dish("chicken", false, 400, MEAT),
      new Dish("french fries", true, 530, OTHER),
      new Dish("rice", true, 350, OTHER),
      new Dish("season fruit", true, 120, OTHER),
      new Dish("pizza", false, 550, OTHER),
      new Dish("prawns", false, 300, FISH),
      new Dish("salmon", false, 450, FISH));

  public static void main(String[] args) {
    // TODO feel free to call any method here to experiment it
  }

  // region === .filter() ===
  public static List<Dish> lowCalories() {
    // TODO select the low-calories (<400) items
    return menu.stream().filter(dish -> dish.getCalories() < 400).toList();
  }

  public static List<Dish> highCalorie() {
    // TODO find out three dishes with > 400 calories
    return menu.stream().filter(dish -> dish.getCalories() > 400).toList();
  }

  public static List<Dish> vegetarian() {
    // TODO find vegetarian dishes
    return menu.stream().filter(Dish::isVegetarian).toList();
  }

  public static List<Dish> nonFish() {
    // TODO dishes except FISH
    return menu.stream().filter(dish -> dish.getType() != FISH).toList();
  }

  public static List<Dish> multiWord() {
    // TODO dishes with name containing >= 2 words
    return menu.stream().filter(dish -> dish.getName().contains(" ")).toList();
  }
  // endregion

  // region === .map ===
  public static List<String> names() {
    // TODO return the names of all the dishes
    return menu.stream().map(Dish::getName).toList();
  }

  public static List<Type> types() {
    // TODO return the types of all the dishes
    return menu.stream().map(Dish::getType).toList();
  }

  public static List<String> namesOfVegetarianDishes() {
    // TODO return the names of all vegetarian dishes
    return menu.stream().filter(Dish::isVegetarian).map(Dish::getName).toList();
  }

  public static List<String> namesOfLowCalories() {
    // TODO return the names of all dishes < 400 calories
    return menu.stream().filter(dish -> dish.getCalories() < 400).map(Dish::getName).toList();
  }

  public static List<String> namesInUpper() {
    // TODO return the names of all dishes in UPPERCASE
    return menu.stream().map(dish -> dish.getName().toUpperCase()).toList();
  }

  public static List<Dish> newDishesWithNameUpperCase() {
    // TODO return a list of new dishes that have the name put in upper-case (and the same old attributes).
    return menu.stream().map(dish -> new Dish(dish.getName().toUpperCase(), dish.isVegetarian(), dish.getCalories(), dish.getType())).toList();
  }

  public static List<Type> allTypesDistinct() {
    // TODO return types of all the dishes in the menu. Your result should not contain duplicates
    // Hint: use .distinct()
    return menu.stream().map(Dish::getType).distinct().toList();
  }

  public static Set<Type> allTypesAsSet() {
    // TODO return types of all the dishes in the menu as a set
    // Hint: use .collect(Collectors.toSet())
    return menu.stream().map(Dish::getType).collect(toSet());
  }
  // endregion

  // region === .anyMatch / .noneMatch / .allMatch ===
  public Boolean anyVegetarian() {
    // TODO return true if I have any vegetarian dish
    return menu.stream().anyMatch(Dish::isVegetarian);
  }

  public Boolean noneFish() {
    // TODO return true if all fish dishes have < 500 calories
    return menu.stream().filter(dish -> dish.getType() == FISH).allMatch(dish -> dish.getCalories() < 500);
  }

  public Boolean vegetarianArentMeat() {
    // TODO true if no vegetarian dish has type = MEAT
    return menu.stream().filter(Dish::isVegetarian).noneMatch(dish -> dish.getType() == MEAT);
  }

  public Boolean allAreUnder1000cal() {
    // TODO return true if all dishes have < 1000 calories
    return menu.stream().allMatch(dish -> dish.getCalories() < 1000);
  }

  public Boolean kForMeat() {
    // TODO return true if all dishes with name containing 'k' have the type MEAT
    return menu.stream().filter(dish -> dish.getName().contains("k")).allMatch(dish -> dish.getType() == MEAT);
  }
  // endregion

  // region === .findFirst ===
  public static Dish lowCaloriesMeat() {
    // TODO return any dish of type MEAT with under 500 calories. If none matches, throw an exception.
    // Hint: use .findFirst() & .orElseThrow()
    return menu.stream()
        .filter(dish -> dish.getType() == MEAT)
        .filter(dish -> dish.getCalories() < 500)
        .findFirst()
        .orElseThrow();
  }
  public static Dish fries() {
    // TODO return any dish with name containing fries. If none matches, return null;
    // Hint: use .orElse(null)
    return menu.stream()
        .filter(dish -> dish.getName().contains("fries"))
        .findFirst()
        .orElse(null);
  }

  public int pizzaCalories() {
    // TODO return the calories of the dish called 'pizza'
    return menu.stream()
        .filter(dish -> "pizza".equals(dish.getName()))
        .findFirst().map(Dish::getCalories)
        .orElseThrow();
  }
  // endregion

  // region === .max(c) & .sorted(c) with c=Comparator.comparing(element->sorting key) ===
  public static List<Dish> sortedByName() {
    // TODO return dishes sorted by name
    return menu.stream()
        .sorted(Comparator.comparing(Dish::getName))
        .toList();
  }

  public static List<Dish> sortedByCalories() {
    // TODO return dishes sorted by name, ascending (low-->high)
    return menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories))
        .toList();
  }

  public static List<Dish> sortedByCaloriesDescending() {
    // TODO return dishes sorted by calories, descending (high-->low)
    return menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories).reversed())
        .toList();
  }

  public static List<Dish> sortedByTypeThenByName() {
    // TODO return dishes sorted first by type name, then by name ascending
    // Hint: see tests for expected result
    return menu.stream()
        .sorted(Comparator.comparing(Dish::getType).thenComparing(Dish::getName))
        .toList();
  }

  public static Dish maxCaloric() {
    // TODO return the dish with the most calories
    return menu.stream()
        .max(Comparator.comparing(Dish::getCalories))
        .orElseThrow();
  }

  public static List<Dish> top3Caloric() {
    // TODO return the top 3 dishes with the most calories
    // Hint: use .limit()
    return menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories).reversed())
        .limit(3)
        .toList();
  }

  public static Dish secondMostCaloric() {
    // TODO return the 2nd highest caloric dish
    // Hint: use .limit() + .skip()
    return menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories).reversed())
        .skip(1)
        .findFirst()
        .orElseThrow();
  }

  public static List<Dish> secondAndThirdMostCaloric() {
    // TODO find out the 2nd and 3rd most caloric items
    return menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories).reversed())
        .skip(1)
        .limit(2)
        .toList();
  }

  public String higherVegetarianCalories() {
    // TODO return the name of the most caloric vegetarian dish
    return menu.stream()
        .filter(Dish::isVegetarian)
        .max(Comparator.comparing(Dish::getCalories))
        .map(Dish::getName)
        .orElseThrow();
  }

  public static List<Dish> meatOptions() {
    // TODO find 2 dishes with meat. Don't return more than 2.
    return menu.stream()
        .filter(dish -> dish.getType() == MEAT)
        .limit(2)
        .toList();
  }
  // endregion

  // region === .collect(Collectors.joining) ===
  public static String namesCommaSeparated() {
    // TODO return names of all dishes, sorted ascending, concatenated by ","
    //  Hint: Use .collect(Collectors.joining(","))
    return menu.stream()
        .map(Dish::getName)
        .sorted()
        .collect(Collectors.joining(","));
  }
  public static String toMenuString() {
    // TODO return a list of strings: transform each Dish to look like
    //  "salmon (300 cal)"
    //  "french fries (530 cal), veg" -> veg means it's vegetarian
    // separate each lines with \n
    return menu.stream()
        .map(dish -> dish.getName() + " (" + dish.getCalories() + " cal)" + (dish.isVegetarian() ? ", veg" : ""))
        .collect(Collectors.joining("\n"));
  }

  public static String toIstambulMenuString() {
    // TODO same as above, but skipping the item named 'pork'
    return menu.stream()
        .filter(dish -> !"pork".equals(dish.getName()))
        .map(dish -> dish.getName() + " (" + dish.getCalories() + " cal)" + (dish.isVegetarian() ? ", veg" : ""))
        .collect(Collectors.joining("\n"));
  }
  // endregion

  // region === Int|DoubleStream / .sum / .average / .reduce ===
  public static int totalCalories() {
    // TODO return the sum of all calories in the menu.
    //  If there is no item in the menu, return 0;
    // Hint: use .mapToInt(Dish::getCalories).sum();
    return menu.stream().mapToInt(Dish::getCalories).sum();
  }
  public static double averageMeatCalories() {
    // TODO return the average of all calories in the menu.
    //  If there is no item in the menu, return 0;
    // Hint: use .mapToInt(..).average();
    return menu.stream().mapToInt(Dish::getCalories).average().orElse(0);
  }

  public static double averageFishCalories() {
    // TODO return the average of all calories of the FISH dishes. orElse(0)
    return menu.stream()
        .filter(dish -> dish.getType() == FISH)
        .mapToInt(Dish::getCalories)
        .average()
        .orElse(0);
  }

  public static int charactersInTheNames() {
    // TODO return the total of all the name lengths (eg: "pork" has length = 4)
    return menu.stream()
        .map(Dish::getName)
        .mapToInt(String::length)
        .sum();
  }

  public static BigDecimal totalPriceForFish(Map<Dish, BigDecimal> pricePerDish) {
    // TODO return the sum of all the prices of the dishes.
    //  The prices are provided as a method parameter
    return menu.stream()
        .filter(dish -> dish.getType() == FISH)
        .map(pricePerDish::get)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
  // endregion
}
