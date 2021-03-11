package digital.pranavr.taskr.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "isImportant")
    private boolean isImportant;

    public Task(int id, String name, String description, boolean isImportant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isImportant = isImportant;
    }

    @Ignore
    public Task(String name, String description, boolean isImportant) {
        this.name = name;
        this.description = description;
        this.isImportant = isImportant;
    }

    @Override
    public String toString() {
        String isImportantInYesNo = this.isImportant ? "yes" : "no";
        return "Task: " + this.name + "\nDescription: " + this.description + "\nImportant: " + isImportantInYesNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
