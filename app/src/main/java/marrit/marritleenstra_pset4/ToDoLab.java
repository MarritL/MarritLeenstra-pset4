package marrit.marritleenstra_pset4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import marrit.marritleenstra_pset4.database.ToDoDBHelper;

/**
 * Created by Marrit on 12-10-2017.
 * The To-Do lab contains all the methods to manipulate the data stash for ToDoItem objects.
 */

public class ToDoLab {

    private static ToDoLab sToDoLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ToDoLab get(Context context) {
        if (sToDoLab == null){
            sToDoLab = new ToDoLab(context);
        }
        return sToDoLab;
    }

    private ToDoLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ToDoDBHelper(mContext);
                .getWritableDatabase();
    }
}
