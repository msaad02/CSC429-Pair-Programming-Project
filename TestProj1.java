import model.Book;

import java.util.Properties;

public class TestProj1 {
    public static void main(String[] args) {
        Book book = new Book("Me", "Myself");
        try {
            book = new Book("100");
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(book);

        // Trying persistent state stuff
        System.out.println("PART 2 WITH PERSISTENT STATE INFO...\n");

        Properties bookState = book.getPersistentState();

        System.out.println(bookState);

        // Get Author

    }
}
