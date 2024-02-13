import exception.InvalidPrimaryKeyException;
import model.Book;
import model.Patron;
import model.BookCollection;
import java.util.Vector;
import java.util.Properties;
import java.util.Scanner;

public class TestProj1 {
    public static void main(String[] args) {

        // Init BookCollection
        BookCollection bookCollection = new BookCollection();

        // Init Scanner obj
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Pair Programming 1 Assignment!");


        int userOption;
        String userInput = "Y";
        boolean printList;
        Vector<Book> bookList = null;
        Vector<Patron> patronList = null;

        while (userInput.equals("Y") || userInput.equals("y")) {

            System.out.println("\nPlease enter one of the following options.");

            // Books
            System.out.println("1) findBooksOlderThanDate");
            System.out.println("2) findBooksNewerThanDate");
            System.out.println("3) findBooksWithTitleLike");
            System.out.println("4) findBooksWithAuthorLike");

            // Patrons
//        System.out.println("5) findPatronsOlderThan");
//        System.out.println("6) findPatronsYoungerThan");
//        System.out.println("7) findPatronsAtZipCode");
//        System.out.println("8) findPatronsWithNameLike");

            System.out.print("\nChoice: ");


            userOption = input.nextInt();


            printList = true;

            if (userOption == 1) { // findBooksOlderThanDate

                System.out.print("\nYou have selected 'findBooksOlderThanDate'. Please enter a year: ");
                userInput = input.next();

                bookList = bookCollection.findBooksOlderThanDate(userInput);

                if (bookList.isEmpty()) {
                    printList = false;
                    System.out.println("No books are older than " + userInput);
                }

            } else if (userOption == 2) { // findBooksNewerThanDate

                System.out.print("\nYou have selected 'findBooksNewerThanDate'. Please enter a year: ");
                userInput = input.next();

                bookList = bookCollection.findBooksNewerThanDate(userInput);

                if (bookList.isEmpty()) {
                    printList = false;
                    System.out.println("No books are newer than " + userInput);
                }

            } else if (userOption == 3) { // findBooksWithTitleLike

                System.out.print("\nYou have selected 'findBooksWithTitleLike'. Please enter a phrase: ");
                userInput = input.next();

                bookList = bookCollection.findBooksWithTitleLike(userInput);

                if (bookList.isEmpty()) {
                    printList = false;
                    System.out.println("No titles contain the phrase '" + userInput + "'.");
                }

            } else if (userOption == 4) { // findBooksWithAuthorLike

                System.out.print("\nYou have selected 'findBooksWithAuthorLike'. Please enter a phrase: ");
                userInput = input.next();

                bookList = bookCollection.findBooksWithAuthorLike(userInput);

                if (bookList.isEmpty()) {
                    printList = false;
                    System.out.println("No authors contain the phrase '" + userInput + "'.");
                }

            } else {
                printList = false;
                System.out.println("ERROR: " + userOption + " is NOT YET IMPLEMENTED.");
            }


            // Print the bookList?
            if (printList) {
                for (int i = 0; i < bookList.size(); i++) {
                    System.out.println(bookList.elementAt(i));
                }
            }

            // Prompt user if they would like to run again.
            System.out.print("\nWould you like to run again? Y/n: ");
            userInput = input.next();
        }

        System.out.println("\nBye!");
    }
}
