package model;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import model.model2.EntityBase;

public class Patron extends EntityBase {
    // Class vars

    // Table name in DB
    private static final String myTableName = "Patron";

    // Holds
    protected Properties dependencies;

    // constructor for this class
    //----------------------------------------------------------
    public Patron(String patronId) throws InvalidPrimaryKeyException {
        super(myTableName);

        // setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (patronId = " + patronId + ")";

        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        // You must get one account at least
        if (allDataRetrieved != null && !allDataRetrieved.isEmpty()) {
            int size = allDataRetrieved.size();

            // There should be EXACTLY one account. More than that is an error
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple accounts matching id : " + patronId + " found.");
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
            throw new InvalidPrimaryKeyException("No account matching id : " + patronId + " found.");
        }
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
        return "{" +
                "PatronID: " + (String) this.getState("patronId") +
                ", Name: " + (String) this.getState("name") +
                ", Address:" + (String) this.getState("address") +
                ", City" + (String) this.getState("city") +
                ", State Code: " + (String) this.getState("stateCode") +
                ", Zip Code: " + (String) this.getState("zip") +
                ", Email: " + (String) this.getState("email") +
                ", Date of Birth: " + (String) this.getState("dateOfBirth") +
                ", Status: " + (String) this.getState("status") +
                "}";
    }
}