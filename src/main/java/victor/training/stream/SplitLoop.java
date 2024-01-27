package victor.training.stream;

import victor.training.stream.support.Order;

import java.util.List;

import static victor.training.stream.support.Order.PaymentMethod.CARD;

public class SplitLoop {

  public Result blob(List<Order> orders) {
    double cardTotal = 0;
    double cashReimbursedTotal = 0;
    for (Order order : orders) {
      if (order.id() == null) {
        throw new IllegalArgumentException("Transient order found");
      }
      if (order.paymentMethod() == CARD) {
        cardTotal += order.total();
        continue;
      }
      if (order.returnReason().isPresent()) {
        cashReimbursedTotal += order.total();
      }
    }
    return new Result(cardTotal, cashReimbursedTotal);
  }
  public record Result(double cardTotal, double cashReimbursedTotal) {

  }
}
