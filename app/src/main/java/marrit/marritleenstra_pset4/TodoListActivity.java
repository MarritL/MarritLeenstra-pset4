package marrit.marritleenstra_pset4;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mAdapter;
    private Button mButtonAdd;
    private List<ToDoItem> toDoItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mToDoRecyclerView = (RecyclerView) findViewById(R.id.todo_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
        setUpItemTouchHelper();

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

            if (mToDoItem.getCompleted()) {
                mCBToDoItem.setChecked(true);
                mTitleTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                mCBToDoItem.setChecked(false);
            }

            mCBToDoItem.setOnCheckedChangeListener(new MyCheckBoxListener());

        }

        // checkbox listener
        public class MyCheckBoxListener implements CompoundButton.OnCheckedChangeListener {

            @Override
            public void onCheckedChanged(CompoundButton checkbox, boolean isChecked) {
                if (isChecked) {
                    mToDoItem.setCompleted(true);
                    ToDoLab.get(TodoListActivity.this).updateToDoItem(mToDoItem);
                    mTitleTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    mToDoItem.setCompleted(false);
                    mTitleTextView.setPaintFlags(0);
                }

            }

        }

    }

    public class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder> {
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

                // check if user gave To-Do title
                if (!mTitle.getText().toString().equals("")) {

                    // make To-Do item
                    ToDoItem toDoItem = new ToDoItem();
                    toDoItem.setTitle(mTitle.getText().toString());
                    ToDoLab.get(TodoListActivity.this).addToDo(toDoItem);

                    // display To-Do item
                    updateUI();

                    // empty edit-text
                    mTitle.getText().clear();

                } else {
                    // if user gave no title, yell at him
                    Toast.makeText(TodoListActivity.this, "Give the TODO a title!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    // update recyclerView
    private void updateUI() {
        ToDoLab toDoLab = ToDoLab.get(this);
        toDoItems = toDoLab.getToDoItems();

        if (mAdapter == null) {
            mAdapter = new ToDoAdapter(toDoItems);
            mToDoRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setToDoItems(toDoItems);
            mAdapter.notifyItemInserted(toDoItems.size() - 1);
        }
    }

    // add swipe ability
    // source: https://stackoverflow.com/questions/34735297/recyclerview-and-itemtouchhelper-swipe-to-remove-issue
    private void setUpItemTouchHelper() {

        ToDoLab toDoLab = ToDoLab.get(this);
        toDoItems = toDoLab.getToDoItems();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerview, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                System.out.println("swipedposition =" + swipedPosition);
                ToDoItem toDo = toDoItems.get(swipedPosition);
                System.out.println("todo = " + toDo);
                ToDoLab.get(TodoListActivity.this).deleteToDo(toDo);
                toDoItems.remove(swipedPosition);
                Log.d("SWIPE", "deleted");
                mAdapter.setToDoItems(toDoItems);
                mAdapter.notifyItemRemoved(swipedPosition);


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mToDoRecyclerView);
    }







}
