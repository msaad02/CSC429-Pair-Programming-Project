import exception.InvalidPrimaryKeyException;
import model.Book;
import model.Patron;
import model.BookCollection;
import java.util.Vector;
import java.util.Properties;

public class TestProj1 {

    // CHECKING THAT BOOK COLLECTION WORKS
    public static void main(String[] args) {
        BookCollection bookList = new BookCollection();;

        Vector<Book> oldBooks = bookList.findBooksNewerThanDate("1950");

        for (int i = 0; i < oldBooks.size(); i++) {
            System.out.println(oldBooks.elementAt(i));
        }
    }


//    // CHECKING THAT WE CAN PULL BOOKS AND PATRONS
//    public static void main(String[] args) {
//        // BOOK CHECK
//        Book book = null;
//        try {
//            book = new Book("101");
//        } catch (InvalidPrimaryKeyException e) {
//            System.out.println("Invalid Key");
//            System.exit(1);
//        }
//
//        System.out.println("Book Test:\n" + book);
//
//        // PATRON CHECK
//        Patron patron = null;
//        try {
//            patron = new Patron("101");
//        } catch (InvalidPrimaryKeyException e) {
//            System.out.println("Invalid Key");
//            System.exit(1);
//        }
//
//        System.out.println("\nPatron Check:\n" + patron);
//    }
}
