package marrit.marritleenstra_pset4.database;

/**
 * Created by Marrit on 12-10-2017.
 * Class containing the schema for the database.
 * Based on: Phillips, Stewart, Marsicano (2017). Android Programming. The big nerd ranch guide.
 * 3th edition. Chapter 14.
 */

public class ToDoDBSchema {
    public static final class ToDoTable {
        public static final String NAME = "ToDos";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            //public static final String DATE = "date";
            public static final String COMPLETED = "completed";
        }
    }
}
