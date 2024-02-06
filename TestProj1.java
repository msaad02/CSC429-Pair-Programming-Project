import exception.InvalidPrimaryKeyException;
import model.Book;

public class TestProj1 {
    public static void main(String[] args) {

        Book book = null;

        try {
            book = new Book("100");
        } catch (InvalidPrimaryKeyException e) {
            System.out.println("Invalid Key");
            System.exit(1);
        }

        // Get Title/Author/PubYear
        String title = (String) book.getState("bookTitle");
        String author = (String) book.getState("author");
        String pubYear = (String) book.getState("pubYear");

        String out = "Author: " + author + "; Title: " + title + "; Publication Year: " + pubYear;
        System.out.println(out);
    }
}
