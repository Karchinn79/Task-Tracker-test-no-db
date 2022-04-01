package tasktracker;

import java.util.ArrayList;
import java.util.Objects;

public class Task {
    protected String taskName;
    protected String taskDescription;
    protected int id;
    protected static int counter=1;
    protected String status;
    public Task(String name, String description, String status)
    {
        taskName = name;
        taskDescription = description;
        this.status = status;
        this.id = counter++;
    }
    public Task (){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && taskName.equals(task.taskName) && Objects.equals(taskDescription, task.taskDescription) && status.equals(task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskDescription, id, status);
    }

    @Override
    public String toString() {
        return "\nTask{" +
                "taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                ", ID='" + id + '\'' +
          '}';
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

}


class Epic extends Task{
    int kek = 0;
    public Epic (String name, String description) {

        super(name, description, "NEW");
    }
    public ArrayList<Subtask> subtasksInEpic = new ArrayList();
    public Epic(){
    };

    public void addSubtask(Subtask subtask){
        subtasksInEpic.add(subtask);
    }

}

class Subtask extends Task{
    int epicID;
    public Subtask(String name, String description, String status, int epicID){
        super(name, description, status);
        this.epicID = epicID;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", epicID=" + epicID +
                '}';
    }

    public Subtask(){

    }
    public int getSubtasksEpic(){
        return epicID;
    }
}