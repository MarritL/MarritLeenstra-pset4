package marrit.marritleenstra_pset4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TodoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        // get the To-Do's on the list
        ToDoLab toDoLab = ToDoLab.get(this);

    }


}
