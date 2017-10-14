package marrit.marritleenstra_pset4;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Marrit on 12-10-2017.
 * Class that holds all the information about a TO-DO item.
 */

public class ToDoItem {

    // declare variables of ToDoItem class
    private static UUID mId;
    private static String mTitle;
    //private static Calendar mDate;
    private static Boolean mCompleted;

    // initiate instance of Class with random Id
    public ToDoItem() {
        mId = UUID.randomUUID();
    }

    // getters and setters for all fields
    public static UUID getId() {
        return mId;
    }

    public static String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    /*public static Calendar getDate() {
        return mDate;
    }*/

    /*public void setDate(Calendar date) {
        mDate = date;
    }*/

    public static Boolean getCompleted() {
        return mCompleted;
    }

    public void setCompleted(Boolean completed) {
        mCompleted = completed;
    }
}
