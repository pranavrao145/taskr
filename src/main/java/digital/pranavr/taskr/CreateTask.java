package digital.pranavr.taskr;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import digital.pranavr.taskr.db.Task;
import digital.pranavr.taskr.db.TaskDatabase;

public class CreateTask extends AppCompatActivity {
    EditText et_taskName, emlt_description;
    CheckBox cb_isImportant;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        et_taskName = findViewById(R.id.et_taskName);
        emlt_description = findViewById(R.id.emlt_description);
        cb_isImportant = findViewById(R.id.cb_isImportant);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task;
                boolean success = false;

                try {
                    task = new Task(et_taskName.getText().toString(), emlt_description.getText().toString(), cb_isImportant.isChecked());
                    success = true;
                    Toast.makeText(CreateTask.this, "Success", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    task = new Task("error", "error", false);
                    Toast.makeText(CreateTask.this, "Error", Toast.LENGTH_SHORT).show();
                }

                TaskDatabase taskDatabase = TaskDatabase.getInstance(CreateTask.this);

                if (success) taskDatabase.taskDao().insertTask(task);

                finish();
            }
        });
    }
}