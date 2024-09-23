package victor.training.stream;

import victor.training.stream.support.*;
import victor.training.stream.support.Order.PaymentMethod;
import victor.training.stream.support.Order.Status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Month;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Exercises {

  public List<OrderDto> p1_activeOrders(List<Order> orders) {
    // TODO 1: simplify
    // TODO 2: use the OrderDto constructor
    // TODO 3: use the OrderMapper.toDto method
//    Function<Order, OrderDto> f = order -> toDto(order); // scary in var a function
//    Function<Order, OrderDto> f = order -> toDto(order);


    List<String> strings = new ArrayList<>();
    Consumer<String> a = strings::add; // java ignores your result type
    Predicate<String> a2 = strings::add; // based on a string -> boolean
    Function<String, Boolean> a3 = strings::add;

    long l = System.currentTimeMillis();
    // 1) ref to a static method
    Supplier<Long> timeSupplier = () -> System.currentTimeMillis();
    Supplier<Long> timeSupplier2 = System::currentTimeMillis;

    // 2) ref to an instance method that I don't have yet
    Predicate<Order> p = Order::isCompleted;

    // 3) ref to an instance method ('println') from an instance that I HAVE already (System.out)
    // Note! here the order to print is not yet available
    Order o = new Order();
    Supplier<Boolean> p2 = o::isCompleted;
    Consumer<Order> c = order -> System.out.println(order);
    Consumer<String> cs = order -> System.out.println(order);
    Consumer<Order> c2 = System.out::println;

    // 4) ref to a constructor
    Supplier<Order> s = () -> new Order();
    Supplier<Order> s2 = Order::new;
    Function<Integer, Order> s3 = Order::new;
    BiFunction<Integer, Status, Order> s3_2 = Order::new;
//    Function3,4,5,6,7,8,9,10 // from Jooq or vavr libraries
//   TriFunction<Integer, Status, Order> s3_2 = Order::new;
    Supplier<Order> s4 = () -> new Order(1);

    System.out.println("order");

    Consumer<Order> c3 = order -> order.setTotal(0);


    // "Target Typing" = -> or :: expressions can be assigned to different types
    // the compiler 'converts' that expr to what you declared you need
    Function<Order, Boolean> p21 = Order::isCompleted;
    MyPred p3 = Order::isCompleted;
//    Object p4doesNotCompile = Order::isCompleted;
    Object p5WorksButWeird = (MyPred)Order::isCompleted;

    List<OrderDto> dtos = orders.stream()
        // anonymous interface impl
//        .filter(new Predicate<Order>() {
//          @Override
//          public boolean test(Order order) {
//            return false;
//          }
//        })
//        .filter((Order order) -> { // ~ WHERE..
//              return false;
//            });
//        .filter((Order order) -> order.status()==COMPLETED);
//        .filter(order -> order.isCompleted() )
//        .filter(p) // grab a ref to an instance method from a Type => i will need the instance to call the method on later
//        .filter((Predicate<Order>)p3) // grab a ref to an instance method from a Type => i will need the instance to call the method on later
        .filter(Order::isCompleted)
        .map(orderMapper::toDto)
        .collect(toList());
//    Function<Order, OrderDto> no = Exercises::toDto; // java compiler won't know on what INSTANCE to call that func
//    BiFunction<Exercises, Order, OrderDto> aRefToAInstanceMethodWihoutSpecifyingTheInstance =
//        Exercises::toDto; // to call it later, I will have to provide an instance of Exercises
//    Exercises e = this;
//    OrderDto dto = aRefToAInstanceMethodWihoutSpecifyingTheInstance.apply(e, new Order());
//
//    Function<Order, OrderDto> yessWorksToo=new Exercises()::toDto;
//    Function<Order, OrderDto> yess=this::toDto;
    return dtos;
  }
  // imagine @Inject/@Autowired
  private OrderMapper orderMapper = new OrderMapper();



  public Optional<Order> p2_findOrderById(List<Order> orders, int orderId) {
    // TODO 1: rewrite with streams
    // TODO 2: return Optional<> and fix the tests
//    for (Order order : orders) {
//      if (order.id() == orderId) {
//        return order;
//      }
//    }
//    return orders.parallelStream() // useful when doing HEAVY CPU work per element
    //  .filter(order -> order.id() == orderId)
//      .findAny() // first one found by any threads (of parallelStream) => faster
    return orders.stream() // useful when doing HEAVY CPU work per element
        .filter(order -> order.id() == orderId)
        .findFirst(); // === findAny, but with more exact name if not using paralleStream
  }

  // TODO all the following: rewrite with streams
  public boolean p3_hasActiveOrders(List<Order> orders) {
//    for (Order order : orders) {
//      if (order.isCompleted()) {
//        return true;
//      }
//    }
//    return false;

//    return orders.stream().filter(Order::isCompleted).collect(toList()).size() > 0;
//    return !orders.stream().filter(Order::isCompleted).collect(toList()).isEmpty();
//    return orders.stream().filter(Order::isCompleted).count() > 0;
    return orders.stream().anyMatch(Order::isCompleted); // simpler to read, and less memory wasteful
  }

  /**
   * @return order with the max total()
   * that does NOT contain a special offer line
   */
  public Order p4_maxPriceOrder(List<Order> orders) {
    return orders.stream()
        .filter(order -> !order.hasSpecialOffer())
        .max(comparing(Order::total))
        .orElse(null);
  }

  /**
   * @return last 3 returnReason()s sorted descending by Order.createdOn
   */
  public List<String> p5_last3Orders(List<Order> orders) {
    List<Order> copy = new ArrayList<>(orders);
    copy.sort(new LatestOrderComparator());
    List<String> returnReasons = new ArrayList<>();
    for (Order order : copy) {
      if (order.returnReason().isPresent()) {
        returnReasons.add(order.returnReason().get());
        if (returnReasons.size() == 3) {
          break;
        }
      }
    }
    return returnReasons;
    // Hint: Optional#stream()
  }

  /**
   * @return sum of all Order.total(), truncated to int.
   */
  public int p6_completedTotalSum(List<Order> orders) {
    double sum = 0;
    for (Order order : orders) {
      if (order.isCompleted())
        sum += order.total();
    }
    return (int) sum;
  }

  /**
   * @return the products bought by the customer, with no duplicates, sorted by Product.name
   */
  public List<Product> p7_productsSorted(List<Order> orders) { // TODO simplify
    Set<Product> products = new HashSet<>();
    for (Order order : orders) {
      for (OrderLine line : order.orderLines()) {
        products.add(line.product());
      }
    }
    List<Product> sorted = new ArrayList<>(products);
    sorted.sort((o1, o2) -> o1.name().compareTo(o2.name()));
    return sorted;
  }

  /**
   * see tests for an example
   */
  public Map<PaymentMethod, List<Order>> p8_ordersGroupedByPaymentMethod(List<Order> orders) {
    Map<PaymentMethod, List<Order>> map = new HashMap<>();
    for (Order order : orders) {
      List<Order> list = map.get(order.paymentMethod());
      if (list == null) {
        list = new ArrayList<>();
        map.put(order.paymentMethod(), list);
      }
      list.add(order);
    }
    return map;
  }

  /**
   * @return the total number of products purchased across all orders (see test)
   */
  public Map<Product, Integer> p9_productCount(List<Order> orders) {
    List<OrderLine> allLines = new ArrayList<>();
    for (Order order : orders) {
      allLines.addAll(order.orderLines());
    }
    Map<Product, Integer> result = new HashMap<>();
    for (OrderLine line : allLines) {
      int old;
      if (!result.containsKey(line.product())) {
        result.put(line.product(), 0);
        old = 0;
      } else {
        old = result.get(line.product());
      }
      result.put(line.product(), old + line.count());
    }
    return result;
  }

  /**
   * @return the names of all products from previous exercise, joined with a ","
   */
  public String pA_productNames(List<Order> orders) {
    List<Product> products = p7_productsSorted(orders);
    StringBuilder sb = new StringBuilder();
    for (Product product : products) {
      sb.append(product.name()).append(",");
    }
    sb.deleteCharAt(sb.length() - 1); // remove the last comma
    return sb.toString();
  }

  /**
   * @return orders grouped by Month, and then by PaymentMethod
   */
  public Map<Month, Map<PaymentMethod, List<Order>>> pB_ordersByPaymentPerMonth(List<Order> orders) {
    Map<Month, Map<PaymentMethod, List<Order>>> result = new HashMap<>();
    for (Order order : orders) {
      Map<PaymentMethod, List<Order>> map = result.get(order.createdOn().getMonth());
      if (map == null) {
        map = new HashMap<>();
        result.put(order.createdOn().getMonth(), map);
      }
      List<Order> list = map.get(order.paymentMethod());
      if (list == null) {
        list = new ArrayList<>();
        map.put(order.paymentMethod(), list);
      }
      list.add(order);
    }
    return result;
  }

  /**
   * @return the first cell of a semicolon-separated file, as integers
   */
  public Set<Integer> pC_csvLinesInAllFilesInFolder(File file) throws IOException {
    try (Stream<String> lines = Files.lines(file.toPath())) {
      return lines
          .filter(s -> !s.isBlank())
          .map(line -> Integer.parseInt(line.split(";")[0]))
          .collect(Collectors.toSet());
    }
  }

  /**
   * @return the elements in Fibonacci sequence between startIndex and endIndex
   */
  public List<Integer> pD_fib(int startIndex, int endIndex) {
    List<Integer> result = new ArrayList<>();
    int a = 1;
    int b = 1;
    int c = a + b;
    int index = 0;
    while (index < endIndex) {
      if (index >= startIndex) {
        result.add(a);
      }
      a = b;
      b = c;
      c = a + b;
      index++;
    }
    return result;

  }

  static class LatestOrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
      return o2.createdOn().compareTo(o1.createdOn());
    }
  }

}
