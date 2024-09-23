package victor.training.stream;

import java.util.List;
import java.util.stream.Stream;

public class CreateStream {
  public static void main(String[] args) {
//    Stream.of(1,2,3).forEach(System.out::println);

//    Stream.generate(()-> UUID.randomUUID().toString())
//        .limit(10)
//        .forEach(System.out::println);

    Stream.iterate(1, i -> i + 1)
        .limit(10)
        .forEach(System.out::println);

    Stream.iterate(
        fetchPage(0), // first call
            page1 -> page1.pageNumber < page1.totalPages, // do I have more ?
            page1 -> fetchPage(page1.pageNumber + 1) // fetch next page
    )
        .flatMap(page -> page.rows().stream())
        .forEach(System.out::println); // write it to a file, or my DB...
  }

  public static Page fetchPage(int pageNumber) {
    return new Page(List.of("row1", "row2"), pageNumber, 3);
  }

  record Page(List<String> rows, int pageNumber, int totalPages){}
}
