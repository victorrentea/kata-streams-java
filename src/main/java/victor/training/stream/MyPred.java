package victor.training.stream;

import victor.training.stream.support.Order;

@FunctionalInterface
public interface MyPred {
  boolean ok(Order o);
}
