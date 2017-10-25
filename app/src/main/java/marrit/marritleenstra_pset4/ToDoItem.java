package marrit.marritleenstra_pset4;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Marrit on 12-10-2017.
 * Class that holds all the information about a TO-DO item.
 */

public class ToDoItem {

    // declare variables of ToDoItem class
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Boolean mCompleted;

    // initiate instance of Class with random Id or given Id from database query
    public ToDoItem() {
        this(UUID.randomUUID());
        //mId = UUID.randomUUID();
        mCompleted = false;


    }

    public ToDoItem(UUID id) {
        mId = id;
    }

    // getters and setters for all fields
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Boolean getCompleted() {
        return mCompleted;
    }

    public void setCompleted(Boolean completed) {
        mCompleted = completed;
    }
}
