package model;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import model.model2.EntityBase;

public class Book extends EntityBase {

	// Table name in DB
	private static final String myTableName = "Book";

	// Holds
	protected Properties dependencies;

	// Constructor using AccountNumber ------------------------------------------------
	public Book(String accountNumber) throws InvalidPrimaryKeyException {
		super(myTableName);

		// setDependencies();
		String query = "SELECT * FROM " + myTableName + " WHERE (bookId = " + accountNumber + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// You must get one account at least
		if (allDataRetrieved != null && !allDataRetrieved.isEmpty()) {
			int size = allDataRetrieved.size();

			// There should be EXACTLY one account. More than that is an error
			if (size != 1) {
				throw new InvalidPrimaryKeyException("Multiple accounts matching id : " + accountNumber + " found.");
			} else {
				// copy all the retrieved data into persistent state
				Properties retrievedAccountData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration<?> allKeys = retrievedAccountData.propertyNames();
				while (allKeys.hasMoreElements()) {
					String nextKey = (String) allKeys.nextElement();
					String nextValue = retrievedAccountData.getProperty(nextKey);

					if (nextValue != null) {
						persistentState.setProperty(nextKey, nextValue);
					}
				}
			}
		}
		// If no account found for this username, throw an exception
		else {
			throw new InvalidPrimaryKeyException("No account matching id : " + accountNumber + " found.");
		}
	}

	// Constructor using Properties ------------------------------------------------
	public Book(Properties givenBookData) {
		super(myTableName);

		persistentState = new Properties();

		Enumeration<?> allKeys = givenBookData.propertyNames();
		while (allKeys.hasMoreElements()) {
			String nextKey = (String) allKeys.nextElement();
			String nextValue = givenBookData.getProperty(nextKey);

			if (nextValue != null) {
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}


	public void insertToDB() {

	}

	@Override
	public Object getState(String key) {
		if (key.equals("UpdateStatusMessage"))
			return "Updating status message?";

		return persistentState.getProperty(key);
	}

	@Override
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'stateChangeRequest'");
	}

	@Override
	public String toString() {
		// Get Title/Author/PubYear
		String title = (String) this.getState("bookTitle");
		String author = (String) this.getState("author");
		String pubYear = (String) this.getState("pubYear");

		return "Author: " + author + "; Title: " + title + "; Publication Year: " + pubYear;
	}
}