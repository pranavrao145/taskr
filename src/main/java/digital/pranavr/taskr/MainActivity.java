package digital.pranavr.taskr;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import digital.pranavr.taskr.db.Task;
import digital.pranavr.taskr.db.TaskDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_addNewTask, btn_deleteAll;
    ListView lv_allTasks;
    TaskDatabase taskDatabase;
    SearchView sv_searchBar;

    ArrayAdapter<Task> taskArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addNewTask = findViewById(R.id.btn_addNewTask);
        btn_deleteAll = findViewById(R.id.btn_deleteAll);
        lv_allTasks = findViewById(R.id.lv_allTasks);
        sv_searchBar = findViewById(R.id.sv_searchBar);

        taskDatabase = TaskDatabase.getInstance(MainActivity.this);

        showTasksOnListView(taskDatabase);

        btn_addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivityIntent = new Intent(MainActivity.this, CreateTask.class);
                MainActivity.this.startActivity(nextActivityIntent);
                showTasksOnListView(taskDatabase);
            }
        });

        btn_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDatabase.taskDao().deleteAllTasks();
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                showTasksOnListView(taskDatabase);
            }
        });

        lv_allTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task clickedTask = (Task) parent.getItemAtPosition(position);
                taskDatabase.taskDao().deleteTask(clickedTask);
                showTasksOnListView(taskDatabase);
                Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        sv_searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Task> relevantTasks = searchTasks(query);
                taskArrayAdapter = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1, relevantTasks);
                lv_allTasks.setAdapter(taskArrayAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Task> relevantTasks = searchTasks(newText);
                taskArrayAdapter = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1, relevantTasks);
                lv_allTasks.setAdapter(taskArrayAdapter);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskDatabase = TaskDatabase.getInstance(MainActivity.this);
        showTasksOnListView(taskDatabase);
    }

    private void showTasksOnListView(TaskDatabase taskDatabase) {
        taskArrayAdapter = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1, taskDatabase.taskDao().getTaskList());
        lv_allTasks.setAdapter(taskArrayAdapter);
    }

    private List<Task> searchTasks(String query) {
        List<Task> allTasks = taskDatabase.taskDao().getTaskList();
        List<Task> relevantTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getName().toLowerCase().contains(query) || task.getDescription().toLowerCase().contains(query)) {
                relevantTasks.add(task);
            }
        }

        return relevantTasks;
    }
}