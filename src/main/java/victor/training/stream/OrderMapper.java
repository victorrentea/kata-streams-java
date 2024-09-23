package victor.training.stream;

import victor.training.stream.support.Order;
import victor.training.stream.support.OrderDto;

public class OrderMapper {
  public OrderDto toDto(Order order) {
    return new OrderDto(
        order.total(),
        order.createdOn(),
        order.paymentMethod(),
        order.status());
  }
}
