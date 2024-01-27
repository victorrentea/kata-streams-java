package victor.training.stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import victor.training.stream.SplitLoop.Result;
import victor.training.stream.support.Order;

import java.util.List;

import static victor.training.stream.support.Order.PaymentMethod.CARD;
import static victor.training.stream.support.Order.PaymentMethod.CASH_ON_SITE;

public class SplitLoopTest {
  private final SplitLoop sut = new SplitLoop();

  @Test
  public void cardTotalWithCardPayment() {
    Order order1 = new Order().total(100).paymentMethod(CARD);
    Order order2 = new Order().total(200).paymentMethod(CARD);

    Result result = sut.blob(List.of(order1, order2));

    Assertions.assertThat(result.cardTotal()).isEqualTo(300);
  }

  @Test
  public void cardTotalWithNonCardPayment() {
    Order order = new Order().total(100).paymentMethod(CASH_ON_SITE);

    Result result = sut.blob(List.of(order));

    Assertions.assertThat(result.cardTotal()).isEqualTo(0);
  }

  @Test
  public void cashReimbursedTotalIfReturnReasonPresent() {
    Order cashOrder1 = new Order().total(100).returnReason("Erroneous");
    Order cashOrder2 = new Order().total(50).returnReason("Erroneous");
    Order cardOrder = new Order().total(300).paymentMethod(CARD).returnReason("Erroneous");

    Result result = sut.blob(List.of(cashOrder1, cashOrder2, cardOrder));

    Assertions.assertThat(result.cashReimbursedTotal()).isEqualTo(150);
  }

  @Test
  public void transientOrderThrowsIllegalArgument() {
    Order order = new Order();

    Assertions.assertThatThrownBy(() -> sut.blob(List.of(order)))
        .isInstanceOf(IllegalArgumentException.class);
  }
}