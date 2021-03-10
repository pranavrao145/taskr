package digital.pranavr.taskr;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    Button btn_addNewTask, btn_deleteAll;
    ListView lv_allTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addNewTask = findViewById(R.id.btn_addNewTask);
        btn_deleteAll = findViewById(R.id.btn_deleteAll);
        lv_allTasks = findViewById(R.id.lv_allTasks);

        btn_addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivityIntent = new Intent(MainActivity.this, CreateTask.class);

                MainActivity.this.startActivity(nextActivityIntent);
            }
        });
    }
}