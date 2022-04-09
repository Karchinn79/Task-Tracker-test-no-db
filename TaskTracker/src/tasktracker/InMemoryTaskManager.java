package tasktracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class InMemoryTaskManager implements TaskManager{
    HashMap<Integer, Task> Tasks = new HashMap<>();
    HashMap<Integer, Epic> Epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    ArrayList<String> history = new ArrayList<>();

    public InMemoryTaskManager(){

    }
    public void addTask (Task task)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Название, описание и статус введи да");
        String n = in.nextLine(), a = in.nextLine(), s=in.nextLine();
        task = new Task(n, a , s);
        Tasks.put(task.getId(), task);
    }

    public HashMap getTasks(){
        return Tasks;
    }

    public Task getTaskById(int identifier){
        historyAddT(identifier);
        return Tasks.get(identifier);
    }
    public void removeTaskById(int identifier){
        Tasks.remove(identifier);
    }
    public int getTaskId(){
        System.out.println("Введите ID");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    public void updateTaskByID (){
        System.out.println("Вот список задач: " + Tasks);
        int taskID = getTaskId();
        Task task = Tasks.get(taskID);
        Scanner in = new Scanner(System.in);
        System.out.println("Что изменить? \n 1. Название \n 2. Описание \n 3. Статус");
        int neo = in.nextInt();
        String newDescription = null;
        String newName = null;
        String newStatus = null;
        String readLineEnd = in.nextLine();
        //Вы можете иметь любое количество операторов case.
        switch (neo) {
            case 1 -> {
                System.out.println("Введите новое название");
                newName = in.nextLine();
            }

            case 2 -> {
                System.out.println("Введите новое описание");
                newDescription = in.nextLine();
            }
            case 3 -> {
                System.out.println("Введите новый статус");
                newStatus = in.nextLine();
            }
        }
        if (newName == null){
            newName = task.taskName;
        }
        if (newDescription == null){
            newDescription = task.taskDescription;
        }
        if (newStatus == null){
            newStatus = task.taskDescription;
        }

        removeTaskById(task.getId());
        task = new Task(newName, newDescription , newStatus);
        Tasks.put(task.getId(), task);
    }

    @Override
    public void history() {
        for(int i = 0; i < history.size(); i++){
            System.out.println(history.get(i));
        }
    }

    @Override
    public void historyAddT(int id) {
        if(history.size()<10) {
            history.add(getTaskById(id).toString());
        }
        else {
            history.remove(0);
            history.add(getTaskById(id).toString());
        }
    }
    public void historyAddE(int id) {
        if(history.size()<10) {
            history.add(getTaskById(id).toString());
        }
        else {
            history.remove(0);
            history.add(getTaskById(id).toString());
        }
    }

    //Epics Part
    public void addEpic (Epic epic){
        Scanner in = new Scanner(System.in);
        System.out.println("Название, описание и статус введи да");
        String n = in.nextLine(), a = in.nextLine();
        epic = new Epic(n, a);
        Epics.put(epic.getId(), epic);
    }
    public HashMap getEpics() {
        return Epics;
    }
    public int getEpicid(){
        System.out.println("Введите ID");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    public ArrayList getSubtasksOfEpic(){
        System.out.println("Вот список эпиков: " + Epics);
        int id = getEpicid();
        Epic epic = Epics.get(id);
        return epic.subtasksInEpic;
    }
    public void removeEpicbyID(int identifier){
        Epic epic = Epics.get(identifier);
        Epics.remove(identifier);
        for (int i = 0; i < epic.subtasksInEpic.size(); i++){
            Subtask curSubtask = epic.subtasksInEpic.get(i);
            subtasks.remove(curSubtask.id);
        }
    }

    //Subtasks part
    public void addSubtask (Subtask subtask)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Название, описание статус и ИД эпика введи да");
        System.out.println("Вот список эпиков: " + Epics);
        String n = in.nextLine(), a = in.nextLine(), s=in.nextLine();
        int id = in.nextInt();
        subtask = new Subtask(n, a , s, id);
        subtasks.put(subtask.getId(), subtask);
        Epic epic = Epics.get(id);
        epic.subtasksInEpic.add(subtask);
    }
    public HashMap getSubtasks() {
        return subtasks;
    }
    public int getSubtaskid(){
        System.out.println("Введите ID");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void updateSubtask(int identifier){
        Subtask currentSubtask = subtasks.get(identifier);
        Epic currentEpic = Epics.get(subtasks.get(identifier).epicID);
        System.out.println("Шо хотите сделать? \n1. Имя сменить\n2.Описание сменить\n3. Стутус сменить");
        if (currentSubtask == null) {
            System.out.println("subtask null");
        }
        else {
            Scanner in = new Scanner(System.in);
            int neo = in.nextInt();
            String newname = null;
            String newstatus = null;
            String newdesciption = null;
            String removeEndline = in.nextLine();
            switch (neo) {
                case 1: newname = in.nextLine();
                break;
                case 2: newdesciption = in.nextLine();
                break;
                case 3: newstatus = in.nextLine();
                break;
            }
            if(newname == null){
                newname = currentSubtask.taskName;
            }
            if (newdesciption == null){
                newdesciption = currentSubtask.taskDescription;
            }
            if (newstatus == null){
                newstatus = currentSubtask.status;
            }
            Subtask newSub = new Subtask(newname, newdesciption, newstatus, currentSubtask.epicID);
            removeSubtaskByID(identifier);
            currentEpic.addSubtask(newSub);
            subtasks.put(newSub.getId(), newSub);

            int subsInEpicCount = currentEpic.subtasksInEpic.size();
            int dones = 0;
            for (int i = 0; i < subsInEpicCount; i++)
            {
                if (currentEpic.subtasksInEpic.get(i).status.equals("DONE")){
                    dones++;
                }
            }
            if (dones == subsInEpicCount){
                currentEpic.status = "DONE";
            }
            else{
                currentEpic.status = "IN_PROGRESS";
            }
        }

    }
    public void removeSubtaskByID (int identifier){
        Epic epic = Epics.get(subtasks.get(identifier).epicID);
        int epicindex = epic.subtasksInEpic.indexOf(subtasks.get(identifier));
        epic.subtasksInEpic.remove(epicindex);
        subtasks.remove(identifier);
    }
}