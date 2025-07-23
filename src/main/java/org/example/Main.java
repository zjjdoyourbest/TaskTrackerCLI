package org.example;

import org.example.bean.Task;
import org.example.util.JsonConvert;
import org.example.util.TaskUtil;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String[] commonds= {"add","update","delete","mark-in-progress","mark-done","list"};
        String[] status= {"todo","done","in-progress"};


        Map<Integer,Task> taskArrayList=new HashMap<>();
        taskArrayList=JsonConvert.printJsonToMap();
        Boolean end=true;
        Scanner scanner=new Scanner(System.in);
        String cmd;

        Map<Integer,String> results =new HashMap<>();

        while (end){
            cmd=scanner.nextLine();
            if (cmd !=null){
                   String[] params=cmd.trim().split(" ");

                    // check first param is task-cli
                    if(!"task-cli".equals(params[0])){
                        results.put(1,"The first parameter should be \"task-cli\"");
                        System.out.println(results.get(1));
                    }else {
                        switch (params[1]) {
                            case "add":
                                results = TaskUtil.checkLength(params, 3);
                                if(results.containsKey(1)){
                                    System.out.println(results.get(1));
                                    continue;
                                }else{
                                    int i =taskArrayList.size()+1;
                                    taskArrayList.put(i,new Task(i,params[2],status[0], LocalDateTime.now(),LocalDateTime.now()));
                                    JsonConvert.printMapToJson(taskArrayList);
                                    System.out.println("Task added successfully (ID: "+i+")");
                                }
                                break;
                            case "update":
                                results = TaskUtil.checkLength(params, 4);
                                if(results.containsKey(1)){
                                    System.out.println(results.get(1));
                                    continue;
                                }else{
                                    results =TaskUtil.isExist(taskArrayList,params[2]);
                                    if(results.containsKey(1)){
                                        System.out.println(results.get(1));
                                        continue;
                                    }else{
                                        Task task = taskArrayList.get(Integer.parseInt(params[2]));
                                        task.setDescription(params[3]);
                                        task.setUpdatedAt(LocalDateTime.now());
                                        JsonConvert.printMapToJson(taskArrayList);
                                        System.out.println("Task updated successfully (ID: "+params[2]+")");
                                    }
                                }
                                break;
                            case "delete":
                                results = TaskUtil.checkLength(params, 3);
                                if(results.containsKey(1)){
                                    System.out.println(results.get(1));
                                    continue;
                                }else{
                                    results =TaskUtil.isExist(taskArrayList,params[2]);
                                    if(results.containsKey(1)){
                                        System.out.println(results.get(1));
                                        continue;
                                    }else{
                                        taskArrayList.remove(Integer.parseInt(params[2]));
                                        JsonConvert.printMapToJson(taskArrayList);
                                        System.out.println("Task deleted successfully (ID: "+params[2]+")");
                                    }
                                }
                                break;
                            case "mark-in-progress":
                                results = TaskUtil.checkLength(params, 3);
                                if(results.containsKey(1)){
                                    System.out.println(results.get(1));
                                    continue;
                                }else{
                                    results =TaskUtil.isExist(taskArrayList,params[2]);
                                    if(results.containsKey(1)){
                                        System.out.println(results.get(1));
                                        continue;
                                    }else{
                                        Task task = taskArrayList.get(Integer.parseInt(params[2]));
                                        task.setStatus(status[2]);
                                        task.setUpdatedAt(LocalDateTime.now());
                                        JsonConvert.printMapToJson(taskArrayList);
                                        System.out.println("Task updated successfully (ID: "+params[2]+")");
                                    }
                                }
                                break;
                            case "mark-done":
                                results = TaskUtil.checkLength(params, 3);
                                if(results.containsKey(1)){
                                    System.out.println(results.get(1));
                                    continue;
                                }else{
                                    results =TaskUtil.isExist(taskArrayList,params[2]);
                                    if(results.containsKey(1)){
                                        System.out.println(results.get(1));
                                        continue;
                                    }else{
                                        Task task = taskArrayList.get(Integer.parseInt(params[2]));
                                        task.setStatus(status[1]);
                                        task.setUpdatedAt(LocalDateTime.now());
                                        JsonConvert.printMapToJson(taskArrayList);
                                        System.out.println("Task updated successfully (ID: "+params[2]+")");
                                    }
                                }
                                break;
                            case "list":
                                results = TaskUtil.checkLength(params, 3);
                                if(results.containsKey(1)){
                                    results = TaskUtil.checkLength(params, 2);
                                    if(results.containsKey(1)) {
                                        System.out.println(results.get(1)+" or 3");
                                        continue;
                                    }else {
                                        TaskUtil.printMap(taskArrayList,"");
                                    }
                                }else{
                                    TaskUtil.printMap(taskArrayList,params[2]);
                                }
                                break;
                            default:
                                System.out.println("you can only use command like this "+ Arrays.toString(commonds));
                        }
                    }
            }
        }
    }

}