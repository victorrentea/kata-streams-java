package victor.training.stream;

import java.util.HashSet;
import java.util.Set;

public class MeVsHashSet {
  public static void main(String[] args) {
    Set<String> set = new HashSet<>();
    set.add("a");
    set.add("a");
    set.add("Costin");
    set.add("EmmaXXXXXXX"); // keys are "sorted" by hashcode
    System.out.println(set);
  }
}

