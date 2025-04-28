package console;

import model.Task;
import service.ServiceTaskImpl;
import util.FileManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public  class ConsoleCli {
    public static Scanner scanner= new Scanner(System.in);
    public static ServiceTaskImpl serviceTask = new ServiceTaskImpl();

    public static void inicioConsole(){
        FileManager.checkFilesExistingIfNotCreated();
        System.out.println("Bienvenido a Task-cli \n");
        getInstruccion();
        do{
            System.out.printf("Task-cli: ");
            String line = scanner.nextLine();
            System.out.println(line.indexOf(" "));
            if(line.compareTo("exit")==0) break;
        }while(true);
    }
    private static void getInstruccion(){
        String inst= """
                add theme= desc=   : Instruccion para agregar una tarea con un TEMA y DESCRIPCION; Se debe agregar un espacio y luego escribir 
                                     theme=.... y desc="...."
                update tipo= text= : Instruccion para MODIFICAR el TEMA, DESCRIPCION o estado; Se debe agregar un espacio y luego escribir uno 
                                     de los siguientes tipo=[theme-status-desc] luego un text=....
                delete id=         : Instruccion para ELIMINAR una TAREA; Se debe agregar un espacio y luego escribir id=....
                showAll            : Instruccion para MOSTRAR todas las TAREAS creadas
                show tema=         : Instruccion para MOSTRAR todas las TAREAS que tengan el TEMA escogido; Se debe agregar un espacio tema=....
                show status=       : Instruccion para MOSTRAR todas las tareas que tengan el ESTADO escogido; Se debe agregar un espacio status=.....
                show tema= status= : Instruccion para MOSTRAR todas las tareas que tengan el ESTADO  y TEMA escogidos Se debe agregar un espacio 
                                     tema=.... y status=.....
                help               : Muestra las Instrucciones disponibles
                """;
        System.out.println(inst);
    }
    private static void addTaskInst(String theme,String description) throws IOException {
        serviceTask.addTask(theme,description);
    }
    private static void deleteTaskInst(String id){
        serviceTask.deleteTask(id);
    }
    private static void updateInst(String id,String updateType ,String text){
        switch (updateType.toLowerCase()){
            case "theme"-> serviceTask.updateTheme(id,text);
            case "status"-> serviceTask.updateTodo(id,text);
            default -> serviceTask.updateDescription(id,text);
        }
    }
    private static void getFilteredThemeOrTodoTask(String type,String text){
        List<Task> tasks = switch (type.toLowerCase()){
            case "theme"-> serviceTask.getFilteredListByTheme(text);
            default -> serviceTask.getFilteredListByTodo(text);
        };
        System.out.printf("Task filtrados por %s: %s \n",type,text);
        tasks.forEach(System.out::println);
    }
    private static void getFilteredThemeAndTodoTask(String theme,String todo){
        List<Task> tasks = serviceTask.getFilteredListByThemeAndTodo(theme,todo);
        System.out.printf("Task filtrados con tema: %s y estado: %s\n",theme,todo);
        tasks.forEach(System.out::println);
    }

}
