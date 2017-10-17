package marrit.marritleenstra_pset4;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private RecyclerView mToDoRecyclerView;
    private List<ToDoItem> mToDoListTest;
    private ToDoAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);



        // get the To-Do's on the list
        ToDoLabTest toDoLabTest = ToDoLabTest.get(this);
        mToDoListTest = ToDoLabTest.getToDos();
        System.out.println(mToDoListTest);

        mToDoRecyclerView = (RecyclerView) findViewById(R.id.todo_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initiate the ListAdapter
        mAdapter = new ToDoAdapter(mToDoListTest);
        mToDoRecyclerView.setAdapter(mAdapter);

        // display list


    }

    private class ToDoHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private ToDoItem mToDoItem;

        public ToDoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_todo, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.todo_title);

        }

        public void bind(ToDoItem toDoItem) {
            mToDoItem = toDoItem;
            mTitleTextView.setText(mToDoItem.getTitle());
            if (mToDoItem.getCompleted()) {
                mTitleTextView.setPaintFlags(mTitleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);;
            }

        }
    }

    private class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder> {
        private List<ToDoItem> mToDoItems;

        public ToDoAdapter(List<ToDoItem> todoItems) {
            mToDoItems = todoItems;
        }

        @Override
        public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            return new ToDoHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ToDoHolder holder, int position) {
            ToDoItem toDoItem = mToDoItems.get(position);
            holder.bind(toDoItem);

        }

        @Override
        public int getItemCount() {
            return mToDoItems.size();
        }
    }



}
