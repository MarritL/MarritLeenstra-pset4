package marrit.marritleenstra_pset4;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mAdapter;
    private Button mButtonAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mToDoRecyclerView = (RecyclerView) findViewById(R.id.todo_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();

        mButtonAdd = (Button) findViewById(R.id.Button_add);
        mButtonAdd.setOnClickListener(new AddOnClickListener());

    }

    private class ToDoHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private ToDoItem mToDoItem;
        private CheckBox mCBToDoItem;

        public ToDoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_todo, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.todo_title);
            mCBToDoItem = (CheckBox) itemView.findViewById(R.id.CB_item);
        }

        public void bind(ToDoItem toDoItem) {
            mToDoItem = toDoItem;
            mTitleTextView.setText(mToDoItem.getTitle());

            mCBToDoItem.setOnCheckedChangeListener(new MyCheckBoxListener());

        }

        // checkbox listener
        public class MyCheckBoxListener implements CompoundButton.OnCheckedChangeListener {

            @Override
            public void onCheckedChanged(CompoundButton checkbox, boolean isChecked) {
                if (isChecked) {
                    mToDoItem.setCompleted(true);
                } else {
                    mToDoItem.setCompleted(false);
                }

                if (mToDoItem.getCompleted()) {
                    mTitleTextView.setPaintFlags(mTitleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    mTitleTextView.setPaintFlags(mTitleTextView.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
                }

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

        public void setToDoItems(List<ToDoItem> toDoItems) {
            mToDoItems = toDoItems;
        }
    }

    // listener to add new To-Do
    public class AddOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == findViewById(R.id.Button_add)) {

                EditText mTitle = (EditText) findViewById(R.id.editText_add_title);

                if (mTitle != null) {
                    ToDoItem toDoItem = new ToDoItem();
                    toDoItem.setTitle(mTitle.getText().toString());
                    toDoItem.setDate(new Date());
                    toDoItem.setCompleted(false);
                    ToDoLab.get(TodoListActivity.this).addToDo(toDoItem);

                    updateUI();

                    mTitle.getText().clear();
                }

            }

        }
    }





    // update recyclerview
    private void updateUI() {
        ToDoLab toDoLab = ToDoLab.get(this);
        List<ToDoItem> toDoItems = toDoLab.getToDoItems();

        if (mAdapter == null) {
            Log.d("TODOLIST", "mAdapter==null");
            mAdapter = new ToDoAdapter(toDoItems);
            mToDoRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setToDoItems(toDoItems);
            mAdapter.notifyItemInserted(toDoItems.size() - 1);
            Log.d("TODOLIST", "notifed item inserted");
        }
    }








}
