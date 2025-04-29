package console;

import model.Task;
import service.ServiceTaskImpl;
import util.ConsoleText;
import util.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public  class ConsoleCli {
    public static Scanner scanner= new Scanner(System.in);
    public static ServiceTaskImpl serviceTask = new ServiceTaskImpl();
    public static List<Task> Tareas= new ArrayList<>(serviceTask.getAllTask());
    public static List<Task> TareasFiltradas= new ArrayList<>();
    public static String lastShow="all";


    public static void inicioConsole(){
        FileManager.checkFilesExistingIfNotCreated();
        System.out.println("Bienvenido a Task-cli ");
        ConsoleText.INITINSTRUCTION.printValue();
        System.out.println("Escriba HELP para ver las instrucciones disponibles\n\n");
        do{
            System.out.print("Task-cli: ");
            String line = scanner.nextLine();
            if(line.isEmpty()) continue;
            String []lineSplitted= line.split("/");
            lineSplitted[0]=lineSplitted[0].toUpperCase().replace(" ","");
            try {
                switch (lineSplitted[0]) {
                    case "ADD" -> addTaskInst(lineSplitted[1], lineSplitted[2]);
                    case "UPDATE" -> {
                        updateInst(Integer.parseInt(lineSplitted[1]), lineSplitted[2], lineSplitted[3]);
                        showPertinentId();
                    }
                    case "DELETE" -> {
                        deleteTaskInst(Integer.parseInt(lineSplitted[1]));
                        showPertinentId();
                    }
                    case "SHOWALL" -> getAllTask();
                    case "SHOW" -> getFilteredTodoTask(lineSplitted[1]);
                    case "HELP" -> ConsoleText.INSTRUCCIONES.printValue();
                    case "IDS" -> showIds();
                    case "IDSFIL" -> showIdsFiltered();
                    default -> ConsoleText.INCORRECTA.printValue();
                }
            }catch (Exception e){
                System.out.println("Error en el sistema");
            }
            if(line.compareTo("exit")==0) break;
        }while(true);
    }

    private static void addTaskInst(String theme,String description) throws IOException {
        if(serviceTask.addTask(theme,description) instanceof Task task){
            Tareas.add(task);
            System.out.println("Agregado correctamente");
            task.printTodo();
        }
        else{
            System.out.println("Error al crear Tarea");
        }
    }
    private static void deleteTaskInst(int id){
        String idL= Tareas.get(id).getId();
        serviceTask.deleteTask(idL);
        Tareas.remove(id);
        System.out.println("Eliminado correctamente");
    }
    private static void updateInst(int id,String updateType ,String text){
        String idL= Tareas.get(id).getId();
        Task task = switch (updateType.toLowerCase()){
            case "name"-> serviceTask.updateName(idL,text);
            case "status"-> serviceTask.updateTodo(idL,text);
            default -> serviceTask.updateDescription(idL,text);
        };
        Tareas.remove(id);
        Tareas.add(task);
        System.out.println("Modificado correctamente");
    }
    private static void getFilteredTodoTask(String text){
        List<Task> tasks = serviceTask.getFilteredListByTodo(text);
        if(tasks.isEmpty()){
            System.out.println("No hay tareas guardadas");
        }else{
            TareasFiltradas = tasks.stream().toList();
            System.out.printf("             Task filtrados por %s \n\n\n",text.toUpperCase());
            tasks.forEach(Task::printTodo);
        }
    }
    private static void getAllTask(){
        List<Task> tasks = serviceTask.getAllTask();
        if(tasks.isEmpty()) {
            System.out.println("No hay tareas guardadas");
        }else {
            Tareas = tasks.stream().toList();
            tasks.forEach(Task::printTodo);
        }
    }
    private static void showIds(){
        if(!Tareas.isEmpty()){
        Tareas.forEach((task) -> task.showPrint(Tareas.indexOf(task)));

            lastShow = "all";
        }else{
            System.out.println("             No hay tareas guardadas");
        }
    }

    private static void showIdsFiltered(){
        if(!TareasFiltradas.isEmpty()) {
            TareasFiltradas.forEach((task) -> task.showPrint(Tareas.indexOf(task)));
            lastShow = "fil";
        }else{
            System.out.println("             No hay tareas filtradas");
        }
    }

    private static void showPertinentId(){
        switch (lastShow){
            case"fil"->showIdsFiltered();
            default -> showIds();
        }
    }
}
