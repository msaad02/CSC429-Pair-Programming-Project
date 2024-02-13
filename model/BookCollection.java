package model;

import model.Book;
import model.model2.EntityBase;

// system imports
import java.util.Properties;
import java.util.Vector;

public class BookCollection extends EntityBase {

    private static final String myTableName = "Book";
    private Vector<Book> bookList;

    // ------ START MATT CHANGES ------

    public BookCollection() {
        super(myTableName);
        bookList = new Vector<Book>();
    }

    public Vector<Book> findBooksOlderThanDate(String year) {
        // Reset bookList
        bookList = new Vector<Book>();

        // Pull the data
        String query = "SELECT * FROM " + myTableName + " WHERE pubYear < " + year;
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        // Loop through data received and make fill bookList with Book objects
        for (int i = 0; i < allDataRetrieved.size(); i++) {
            bookList.add(new Book(allDataRetrieved.elementAt(i)));
        }

        // Return all the books
        return bookList;
    }

    public Vector<Book> findBooksNewerThanDate(String year) {
        // Reset bookList
        bookList = new Vector<Book>();

        // Pull the data
        String query = "SELECT * FROM " + myTableName + " WHERE pubYear > " + year;
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        // Loop through data received and make fill bookList with Book objects
        for (int i = 0; i < allDataRetrieved.size(); i++) {
            bookList.add(new Book(allDataRetrieved.elementAt(i)));
        }

        // Return all the books
        return bookList;
    }








    // ------ END MATT CHANGES ------

    @Override
    public Object getState(String key) {
        if (key.equals("Books"))
            return bookList;
        else
        if (key.equals("BookList"))
            return this;
        return null;
    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }
}