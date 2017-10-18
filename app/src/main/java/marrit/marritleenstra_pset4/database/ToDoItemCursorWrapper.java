package marrit.marritleenstra_pset4.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.support.annotation.IntDef;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import marrit.marritleenstra_pset4.ToDoItem;
import marrit.marritleenstra_pset4.database.ToDoDBSchema.ToDoTable;

/**
 * Created by Marrit on 18-10-2017.
 */

public class ToDoItemCursorWrapper extends CursorWrapper {
    public ToDoItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ToDoItem getToDoItem() {
        String uuidString = getString(getColumnIndex(ToDoTable.Cols.UUID));
        String title = getString(getColumnIndex(ToDoTable.Cols.TITLE));
        Long date = getLong(getColumnIndex(ToDoTable.Cols.DATE));
        int isCompleted = getInt(getColumnIndex(ToDoTable.Cols.COMPLETED));

        ToDoItem toDoItem = new ToDoItem(UUID.fromString(uuidString));
        toDoItem.setTitle(title);
        toDoItem.setDate(new Date(date));
        toDoItem.setCompleted(isCompleted != 0);



        return toDoItem;
    }
}
