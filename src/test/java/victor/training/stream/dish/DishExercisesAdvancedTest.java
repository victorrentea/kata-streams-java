package victor.training.stream.dish;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static victor.training.stream.dish.Dish.Type.*;
import static victor.training.stream.dish.DishExerciseTest.*;

public class DishExercisesAdvancedTest {
  @Nested
  class FlatMapTests {
    @Test
    void allAllergens() {
      assertThat(DishExerciseAdvanced.allAllergens())
          .containsExactlyInAnyOrder("milk", "gluten", "fish", "seafood", "strawberry");
    }

    @Test
    void allWords() {
      assertThat(DishExerciseAdvanced.allWords())
          .containsExactlyInAnyOrder("pork", "beef", "chicken", "french", "fries", "rice", "season", "fruit", "pizza", "prawns", "salmon");
    }
  }

  @Nested
  class GroupingTests {
    @Test
    void dishByName() {
      assertThat(DishExerciseAdvanced.dishByName())
          .containsEntry("pork", pork)
          .containsEntry("beef", beef)
          .containsEntry("chicken", chicken)
          .containsEntry("french fries", frenchFries)
          .containsEntry("rice", rice)
          .containsEntry("season fruit", seasonFruit)
          .containsEntry("pizza", pizza)
          .containsEntry("prawns", prawns)
          .containsEntry("salmon", salmon);
    }

    @Test
    void dishesByType() {
      assertThat(DishExerciseAdvanced.dishesByType())
          .containsEntry(MEAT, List.of(pork, beef, chicken))
          .containsEntry(FISH, List.of(prawns, salmon))
          .containsEntry(OTHER, List.of(frenchFries, rice, seasonFruit, pizza));
    }

    @Test
    void numberOfDishesByType() {
      assertThat(DishExerciseAdvanced.numberOfDishesByType())
          .containsEntry(MEAT, 3L)
          .containsEntry(FISH, 2L)
          .containsEntry(OTHER, 4L);
    }

    @Test
    void totalCaloriesByType() {
      assertThat(DishExerciseAdvanced.totalCaloriesByType())
          .containsEntry(MEAT, 1900L)
          .containsEntry(FISH, 750L)
          .containsEntry(OTHER, 1550L);
    }

    @Test
    void dishNamesByCategory() {
      assertThat(DishExerciseAdvanced.dishNamesByCategory())
          .containsEntry(MEAT, Set.of("pork", "beef", "chicken"))
          .containsEntry(FISH, Set.of("prawns", "salmon"))
          .containsEntry(OTHER, Set.of("french fries", "rice", "season fruit", "pizza"));
    }
  }
}
