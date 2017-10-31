package marrit.marritleenstra_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import marrit.marritleenstra_pset4.database.ToDoDBHelper;
import marrit.marritleenstra_pset4.database.ToDoDBSchema.ToDoTable;
import marrit.marritleenstra_pset4.database.ToDoItemCursorWrapper;

/**
 * Created by Marrit on 12-10-2017.
 * The To-Do lab contains all the methods to manipulate the data stash for ToDoItem objects.
 */

class ToDoLab {

    // declare variables
    private static ToDoLab sToDoLab;
    private static SQLiteDatabase mDatabase;

    // get the ToDoLab object (only one possible)
    static ToDoLab get(Context context) {
        if (sToDoLab == null){
            sToDoLab = new ToDoLab(context);
        }
        return sToDoLab;
    }

    // open (and if needed create) a database
    private ToDoLab(Context context) {
        Context context1 = context.getApplicationContext();
        ToDoDBHelper helper = new ToDoDBHelper(context1);
        mDatabase = helper.getWritableDatabase();
    }

    // add a row to the database
    void addToDo(ToDoItem toDo) {
        ContentValues values = getContentValues(toDo);

        mDatabase.insert(ToDoTable.NAME, null, values);
    }

    // delete a row from the database
    void deleteToDo(ToDoItem toDo) {
        String idString = String.valueOf(toDo.getId());

        mDatabase.delete(ToDoTable.NAME, ToDoTable.Cols._id + " = ?",
                new String[] { idString });

    }

    // read the ToDoItems from the database and put in list
    List<ToDoItem> getToDoItems() {
        List<ToDoItem> toDoItems = new ArrayList<>();

        ToDoItemCursorWrapper cursor = queryToDoItems(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                toDoItems.add(cursor.getToDoItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return toDoItems;
    }

    // get one to do item
    public ToDoItem getToDoItem(int id) {

        ToDoItemCursorWrapper cursor = queryToDoItems(
                ToDoTable.Cols._id + " = ?",
                new String[] {String.valueOf(id) }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getToDoItem();
        } finally {
            cursor.close();
        }
    }

    // update a row in the database
    void updateToDoItem(ToDoItem toDo) {
        String idString = String.valueOf(toDo.getId());
        ContentValues values = getContentValues(toDo);

        mDatabase.update(ToDoTable.NAME, values,
                ToDoTable.Cols._id + " = ?",
                new String[] { idString });

    }

    // create a ContentValues for a to-do item
    private static ContentValues getContentValues(ToDoItem toDo) {
        ContentValues values = new ContentValues();
        values.put(ToDoTable.Cols.TITLE, toDo.getTitle());
        values.put(ToDoTable.Cols.COMPLETED, toDo.getCompleted() ? 1: 0);

        return values;
    }

    // query database
    private static ToDoItemCursorWrapper queryToDoItems(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ToDoTable.NAME,
                null, // select all columns
                whereClause,
                whereArgs,
                null, // group by
                null, // having
                null // orderBy
        );
        return new ToDoItemCursorWrapper(cursor);
    }
}
