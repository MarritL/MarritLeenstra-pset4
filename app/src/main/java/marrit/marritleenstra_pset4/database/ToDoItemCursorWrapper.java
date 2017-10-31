package marrit.marritleenstra_pset4.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import marrit.marritleenstra_pset4.ToDoItem;
import marrit.marritleenstra_pset4.database.ToDoDBSchema.ToDoTable;

/**
 * Created by Marrit on 18-10-2017.
 * A CursorWrapper to pull out data of the database.
 * Based on: Phillips, Stewart, Marsicano (2017). Android Programming. The big nerd ranch guide.
 * 3th edition. Chapter 14.
 */

public class ToDoItemCursorWrapper extends CursorWrapper {
    public ToDoItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ToDoItem getToDoItem() {
        String idString = getString(getColumnIndex(ToDoTable.Cols._id));
        String title = getString(getColumnIndex(ToDoTable.Cols.TITLE));
        int isCompleted = getInt(getColumnIndex(ToDoTable.Cols.COMPLETED));

        ToDoItem toDoItem = new ToDoItem(Integer.valueOf(idString));
        toDoItem.setTitle(title);
        toDoItem.setCompleted(isCompleted != 0);

        return toDoItem;
    }
}
