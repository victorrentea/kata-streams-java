package victor.training.stream.support;

import java.math.BigDecimal;
import java.time.LocalDate;

import static victor.training.stream.support.Order.PaymentMethod;
import static victor.training.stream.support.Order.Status;

public record OrderDto(double totalPrice,
                       LocalDate creationDate,
                       PaymentMethod paymentMethod,
                       Status status) {

  public OrderDto(Order order) {
    this(order.total(),
        order.createdOn(),
        order.paymentMethod(),
        order.status());
  }
}
