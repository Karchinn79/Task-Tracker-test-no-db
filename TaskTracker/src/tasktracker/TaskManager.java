package tasktracker;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    void addTask(Task task);
    HashMap getTasks();
    Task getTaskById(int id);
    void removeTaskById(int id);
    int getTaskId();
    void updateTaskByID();
    void addEpic(Epic epic);
    HashMap getEpics();
    int getEpicid();
    ArrayList getSubtasksOfEpic();
    void removeEpicbyID(int id);
    void addSubtask(Subtask subtask);
    HashMap getSubtasks();
    int getSubtaskid();
    void updateSubtask(int id);
    void removeSubtaskByID (int id);
    void history();
    void historyAddT(int id);
}
