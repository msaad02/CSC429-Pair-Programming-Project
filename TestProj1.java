import exception.InvalidPrimaryKeyException;
import model.Book;
import model.Patron;

public class TestProj1 {
    public static void main(String[] args) {
        // BOOK CHECK
        Book book = null;
        try {
            book = new Book("101");
        } catch (InvalidPrimaryKeyException e) {
            System.out.println("Invalid Key");
            System.exit(1);
        }

        System.out.println("Book Test:\n" + book);

        // PATRON CHECK
        Patron patron = null;
        try {
            patron = new Patron("101");
        } catch (InvalidPrimaryKeyException e) {
            System.out.println("Invalid Key");
            System.exit(1);
        }

        System.out.println("\nPatron Check:\n" + patron);
    }
}
