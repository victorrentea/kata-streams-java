package victor.training.stream.dish;

import victor.training.stream.dish.Dish.Type;

import java.math.BigDecimal;
import java.util.*;

import static victor.training.stream.dish.Dish.Type.*;

public class DishExercise {
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
    return null;
  }

  public static List<Dish> highCalorie() {
    // TODO find out three high-calorie dishes
    return List.of();
  }

  public static List<Dish> vegetarian() {
    // TODO find vegetarian dishes
    return null;
  }

  public static List<Dish> nonFish() {
    // TODO dishes except FISH
    return null;
  }

  public static List<Dish> multiWord() {
    // TODO dishes with name containing >= 2 words
    return null;
  }
  // endregion

  // region === .map ===
  public static List<String> names() {
    // TODO return the names of all the dishes
    return null;
  }

  public static List<Dish.Type> types() {
    // TODO return the types of all the dishes
    return null;
  }

  public static List<String> namesOfVegetarianDishes() {
    // TODO return the names of all vegetarian dishes
    return null;
  }

  public static List<String> namesOfLowCalories() {
    // TODO return the names of all dishes < 400 calories
    return null;
  }

  public static List<String> namesInUpper() {
    // TODO return the names of all dishes in UPPERCASE
    return null;
  }

  public static List<Dish> newDishesWithNameUpperCase() {
    // TODO return a list of new dishes that have the name put in upper-case (and the same old attributes).
    return null;
  }

  public static List<Type> allTypesDistinct() {
    // TODO return types of all the dishes in the menu. Your result should not contain duplicates
    // Hint: use .distinct()
    return null;
  }

  public static Set<Type> allTypesAsSet() {
    // TODO return types of all the dishes in the menu as a set
    // Hint: use .collect(Collectors.toSet())
    return null;
  }
  // endregion

  // region === .anyMatch / .noneMatch / .allMatch ===
  public Boolean anyVegetarian() {
    // TODO return true if I have any vegetarian dish
    return null;
  }

  public Boolean noneFish() {
    // TODO return true if all fish dishes have < 500 calories
    return null;
  }

  public Boolean vegetarianArentMeat() {
    // TODO true if no vegetarian dish has type = MEAT
    return null;
  }

  public Boolean allAreUnder1000cal() {
    // TODO return true if all dishes have < 1000 calories
    return null;
  }

  public Boolean kForMeat() {
    // TODO return true if all dishes with name containing 'k' have the type MEAT
    return null;
  }
  // endregion

  // region === .findFirst ===
  public static Dish lowCaloriesMeat() {
    // TODO return any dish of type MEAT with under 500 calories. If none matches, throw an exception.
    // Hint: use .findFirst() & .orElseThrow()
    return null;
  }
  public static Dish fries() {
    // TODO return any dish with name containing fries. If none matches, return null;
    // Hint: use .orElse(null)
    return null;
  }

  public Dish pizzaCalories() {
    // TODO return the calories of the dish called 'pizza'
    return null;
  }
  // endregion

  // region === .max(c) & .sorted(c) with c=Comparator.comparing(element->sorting key) ===
  public static List<Dish> sortedByName() {
    // TODO return dishes sorted by name
    return null;
  }

  public static List<Dish> sortedByCalories() {
    // TODO return dishes sorted by name, ascending (low-->high)
    return null;
  }

  public static List<Dish> sortedByCaloriesDescending() {
    // TODO return dishes sorted by calories, descending (high-->low)
    return null;
  }

  public static List<Dish> sortedByTypeThenByName() {
    // TODO return dishes sorted first by type name, then by name ascending
    // Hint: see tests for expected result
    return null;
  }

  public static Dish maxCaloric() {
    // TODO return the dish with the most calories
    return null;
  }

  public static List<Dish> top3Caloric() {
    // TODO return the top 3 dishes with the most calories
    // Hint: use .limit()
    return null;
  }

  public static Dish secondMostCaloric() {
    // TODO return the 2nd highest caloric dish
    // Hint: use .limit() + .skip()
    return null;
  }

  public static List<Dish> secondAndThirdMostCaloric() {
    // TODO find out the 2nd and 3rd most caloric items
    return null;
  }

  public String higherVegetarianCalories() {
    // TODO return the name of the most caloric vegetarian dish
    return null;
  }

  public static List<Dish> meatOptions() {
    // TODO find 2 dishes with meat. Don't return more than 2.
    return null;
  }
  // endregion

  // region === .collect(Collectors.joining) ===
  public static String namesCommaSeparated() {
    // TODO return names of all dishes, sorted ascending, concatenated by ","
    //  Hint: Use .collect(Collectors.joining(","))
    return null;
  }
  public static String toMenuString() {
    // TODO return a list of strings: transform each Dish to look like
    //  "salmon (300 cal)"
    //  "french fries (530 cal), veg" -> veg means it's vegetarian
    // separate each lines with \n
    return null;
  }

  public static String toIstambulMenuString() {
    // TODO same as above, but skipping the item named 'pork'
    return null;
  }
  // endregion

  // region === Int|DoubleStream / .sum / .average / .reduce ===
  public static int totalCalories() {
    // TODO return the sum of all calories in the menu.
    //  If there is no item in the menu, return 0;
    // Hint: use .mapToInt(Dish::getCalories).sum();
    return 0;
  }
  public static int averageMeatCalories() {
    // TODO return the average of all calories in the menu.
    //  If there is no item in the menu, return 0;
    // Hint: use .mapToInt(..).average();
    return 0;
  }

  public static int averageFishCalories() {
    // TODO return the average of all calories of the FISH dishes. orElse(0)
    return 0;
  }

  public static int charactersInTheNames() {
    // TODO return the total of all the name lengths (eg: "pork" has length = 4)
    return 0;
  }

  public static BigDecimal totalPriceForFish(Map<Dish, BigDecimal> pricePerDish) {
    // TODO return the sum of all the prices of the dishes.
    //  The prices are provided as a method parameter
    return null;
  }
  // endregion
}
