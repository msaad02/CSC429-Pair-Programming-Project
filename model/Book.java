package model;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import model.model2.EntityBase;

public class Book extends EntityBase 
{
    private int bookId; // Primary key, auto    -increment
    private String bookTitle;
    private String author;
    private String pubYear;
    private String status; // Possible values: 'Active' or 'Inactive'


    private static final String myTableName = "Book";

	protected Properties dependencies;

	// Temp Constructor
	public Book(String bookTitle, String author) {
		super(myTableName);

		this.bookTitle = bookTitle;
		this.author = author;
	}

	// constructor for this class
	//----------------------------------------------------------
	public Book(String accountNumber) throws InvalidPrimaryKeyException
	{
		super(myTableName);

		// setDependencies();
		String query = "SELECT * FROM " + myTableName + " WHERE (bookId = " + accountNumber + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// You must get one account at least

        // MATT EDIT: Made a comment in class to check that size is > 0 too.
		if (allDataRetrieved != null && allDataRetrieved.size() > 0) // I editted after &&
		{
			int size = allDataRetrieved.size();

			// There should be EXACTLY one account. More than that is an error
			if (size != 1)
			{
				throw new InvalidPrimaryKeyException("Multiple accounts matching id : " + accountNumber + " found.");
			}
			else
			{
				// copy all the retrieved data into persistent state
				Properties retrievedAccountData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedAccountData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedAccountData.getProperty(nextKey);
					// accountNumber = Integer.parseInt(retrievedAccountData.getProperty("accountNumber"));

					if (nextValue != null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}

			}
		}
		// If no account found for this username, throw an exception
		else
		{
			throw new InvalidPrimaryKeyException("No account matching id : " + accountNumber + " found.");
		}
	}

	public Properties getPersistentState() {
		return persistentState;
	}

    @Override
	public Object getState(String key)
	{
		if (key.equals("UpdateStatusMessage"))
			return "Updating status message?";

		return persistentState.getProperty(key);
	}

    @Override
    public void stateChangeRequest(String key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stateChangeRequest'");
    }

	// MATT NOTE: Mitra said this needs to be included in every class that extends EntityBase.
	// I don't see why we don't just include it in EntityBase itself.
	//-----------------------------------------------------------------------------------
	@Override
	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}

	// Can also be used to create a NEW Account (if the system it is part of
	// allows for a new account to be set up)
	//----------------------------------------------------------
	// public Account(Properties props)
	// {
	// 	super(myTableName);

	// 	setDependencies();
	// 	persistentState = new Properties();
	// 	Enumeration allKeys = props.propertyNames();
	// 	while (allKeys.hasMoreElements() == true)
	// 	{
	// 		String nextKey = (String)allKeys.nextElement();
	// 		String nextValue = props.getProperty(nextKey);

	// 		if (nextValue != null)
	// 		{
	// 			persistentState.setProperty(nextKey, nextValue);
	// 		}
	// 	}
	// }

    
}
