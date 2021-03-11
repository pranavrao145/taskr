package digital.pranavr.taskr.db;

import androidx.room.*;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("select * from task")
    List<Task> getTaskList();

    @Query("delete from task")
    void deleteAllTasks();

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

}
