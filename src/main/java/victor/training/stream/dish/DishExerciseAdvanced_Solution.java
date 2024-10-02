package victor.training.stream.dish;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class DishExerciseAdvanced_Solution {

  // region flatMap
  public static final Map<String, List<String>> allergens = Map.of(
      "pizza", List.of("milk", "gluten", "fish", "seafood"),
      "french fries", List.of("gluten"),
      "salmon", List.of("fish"),
      "bread", List.of("gluten"),
      "fruit salad", List.of("strawberry"),
      "prawn", List.of("seafood")
  );

  public static List<String> allAllergens() {
    // TODO return all ingredients for any item in the menu
    //  Look the items up in the allergens map above
    //  Don't return any duplicates
    // Hint: use .flatMap
    return DishExercise.menu.stream()
        .flatMap(dish -> allergens.get(dish.getName()).stream())
        .distinct()
        .toList();
  }

  public static List<String> allWords() {
    // TODO return all the words of all the names of our dishes
    // For example, "season fruit" -> 2 words
    return DishExercise.menu.stream()
        .flatMap(dish -> List.of(dish.getName().split(" ")).stream())
        .toList();
  }

  // endregion

  // region .groupingBy / .toMap
  public static Map<String, Dish> dishByName() {
    // TODO the returned map has under the key=XYZ the Dish with name=XYZ
    // Hint: use .collect(Collectors.toMap(<key-extractor>, dish->dish));
    return DishExercise.menu.stream()
        .collect(toMap(Dish::getName, dish -> dish));
  }

  public static Map<Dish.Type, List<Dish>> dishesByType() {
    // TODO group dishes by type.
    //  For example, in the returned Map, under the key MEAT will be all the Dishes with type=meat
    // Hint: use .collect(Collectors.groupingBy(..))
    // in .. write an expression that returns from one element the gey used for grouping
    return DishExercise.menu.stream()
        .collect(groupingBy(Dish::getType));
  }

  public static Map<Dish.Type, Long> numberOfDishesByType() {
    // TODO group dishes by type and count how many are in each group.
    // Hint: use .collect(Collectors.groupingBy(.. , Collectors.counting()))
    // in .. write an expression that returns from one element the gey used for grouping
    return DishExercise.menu.stream()
        .collect(groupingBy(Dish::getType, counting()));
  }

  public static Map<Dish.Type, Long> totalCaloriesByType() {
    // TODO group dishes by type and sum up the calories of each group
    // Hint: use .collect(Collectors.groupingBy(.. , Collectors.summingInt(Dish::getCalories)))
    return DishExercise.menu.stream()
        .collect(groupingBy(Dish::getType, summingLong(Dish::getCalories)));
  }

  public static Map<Dish.Type, Set<String>> dishNamesByCategory() {
    // TODO group dishes by type and sum up the calories of each group
    // Hint: use .collect(Collectors.groupingBy(.. , Collectors.mapping(Dish::getName, toSet())))
    return DishExercise.menu.stream()
        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toSet())));
  }
  // endregion



}
