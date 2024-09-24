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

    streamRecordsFromRemote()
        .forEach(System.out::println); // write it to a file, or my DB...
  }

  private static Stream<String> streamRecordsFromRemote() {
    return Stream.iterate(
            fetchPage(0), // first call
            prevPage -> prevPage.pageNumber < prevPage.totalPages, // do I have more ?
            prevPage -> fetchPage(prevPage.pageNumber + 1) // fetch next page
        )
        .flatMap(page -> page.rows().stream());
  }

  public static Page fetchPage(int pageNumber) {
    return new Page(List.of("row1", "row2"), pageNumber, 3);
  }

  record Page(List<String> rows, int pageNumber, int totalPages){}
}
