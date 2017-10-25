package marrit.marritleenstra_pset4;

import java.util.UUID;

/**
 * Created by Marrit on 12-10-2017.
 * Class that holds all the information about a TO-DO item.
 */

public class ToDoItem {

    // declare variables of ToDoItem class
    //private UUID mId;
    private int mId;
    private String mTitle;
    private Boolean mCompleted;

    // initiate instance of Class with random Id or given Id from database query
    public ToDoItem() {
        //this(UUID.randomUUID());
        mCompleted = false;


    }

    //public ToDoItem(UUID id) {
    public ToDoItem(int id) {
        mId = id;
    }

    // getters and setters for all fields
    //public UUID getId() {
    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Boolean getCompleted() {
        return mCompleted;
    }

    public void setCompleted(Boolean completed) {
        mCompleted = completed;
    }
}
