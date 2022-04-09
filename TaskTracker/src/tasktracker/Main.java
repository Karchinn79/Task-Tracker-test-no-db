package tasktracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager operator = new InMemoryTaskManager();
        Task taskOp = new Task();
        Epic epicOp = new Epic();
        Subtask subtaskOp = new Subtask();
        int way = -1;

        while(way != 0){
        showMenu();
        Scanner in = new Scanner(System.in);
        way = in.nextInt();
        switch(way) {
            case 0:
                System.out.println("Доброго ранку!");
                break;
            case 1: operator.addTask(taskOp);
                break;
            case 2: operator.addEpic(epicOp);
                break;
            case 3: operator.addSubtask(subtaskOp);
                break;
            case 4:
                System.out.println(operator.getSubtasksOfEpic());
                break;
            case 5: System.out.println(operator.getTasks());
                break;
            case 6:
                System.out.println(operator.getEpics());
                break;
            case 7:
                operator.updateTaskByID();
                break;
            case 8:
                operator.updateSubtask(askID());
                break;
            case 9:
               operator.removeTaskById(operator.getTaskId());
                break;
            case 10:
                System.out.println(operator.getEpics());
                operator.removeEpicbyID(operator.getEpicid());
                break;
            case 11:
                System.out.println(operator.getSubtasks());
                break;
            case 12:
                operator.history();
                break;
            default:
                System.out.println("Такого пункта нет ди нах)");
                break;

        }
        }
    }
    public static void showMenu(){
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Добавить задачу\n2. Добавить эпик\n3. Добавить подзадачу");
        System.out.println("4. Посмотреть список подзадач эпика по ID\n5. Посмотреть список всех задач\n6. Посмотреть список всех эпиков");
        System.out.println("7. Изменить задачу\n8. Изменить подзадачу\n9. Удалить задачу\n10. Удалить Эпик");
        System.out.println("11. showsubtasks\n12. Призвать историю\n 13. ");
    }
    public static int askID()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ID");
        int ID = in.nextInt();
        return ID;
    }
}
