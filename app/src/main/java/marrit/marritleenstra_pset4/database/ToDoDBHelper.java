package marrit.marritleenstra_pset4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import marrit.marritleenstra_pset4.database.ToDoDBSchema.ToDoTable;

/**
 * Created by Marrit on 12-10-2017.
 * Based on: Phillips, Stewart, Marsicano (2017). Android Programming. The big nerd ranch guide.
 * 3th edition. Chapter 14.
 */

public class ToDoDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "ToDoBase.db";

    public ToDoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ToDoTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        ToDoTable.Cols.UUID + ", " +
                        ToDoTable.Cols.TITLE + ", " +
                        ToDoTable.Cols.COMPLETED + ")"
        );




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
