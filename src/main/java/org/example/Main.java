package org.example;

import org.example.bean.Task;
import org.example.util.JsonConvert;
import org.example.util.TaskUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {
        String[] commands = {"add","update","delete","mark-in-progress","mark-done","list"};
        String[] status= {"todo","done","in-progress"};

        Map<Integer,Task> taskArrayList=new HashMap<>();
        taskArrayList=JsonConvert.printJsonToMap();
        boolean end=true;
        Scanner scanner=new Scanner(System.in);
        String cmd;

        Map<Integer,String> results =new HashMap<>();

        System.out.println("TaskTrackerCLI start");

        while (end){
            cmd=scanner.nextLine();
            if (cmd !=null){
                Pattern pattern =Pattern.compile("\"([^\"]*)\"|(\\S+)");
                Matcher matcher=pattern.matcher(cmd);
                ArrayList<String> params=new ArrayList<>();

                while (matcher.find()) {
                    if (matcher.group(1) != null) {
                        // 如果第一个分组非 null（带引号的）
                        params.add(matcher.group(1));
                    } else {
                        // 否则是普通单词
                        params.add(matcher.group(2));
                    }
                }

                    //String[] params=cmd.trim().split(" ");

                    // check first param is task-cli
                    if(!"task-cli".equals(params.get(0))){
                        //Typing exit will terminate the program
                        if("exit".equals(params.get(0))){
                            System.out.println("TaskTrackerCLI stop");
                            end=false;
                            continue;
                        }else {
                            results.put(1, "The first parameter should be \"task-cli\"");
                            System.out.println(results.get(1));
                        }
                    }else {
                        //Check the number of command-line arguments.
                        switch (params.get(1)) {
                            case "add":
                            case "delete":
                            case "mark-in-progress":
                            case "mark-done":
                                results = TaskUtil.checkLength(params, 3);
                                if(results.containsKey(1)){
                                    System.out.println(results.get(1));
                                    continue;
                                }
                                break;
                            case "update":
                                results = TaskUtil.checkLength(params, 4);
                                if(results.containsKey(1)){
                                    System.out.println(results.get(1));
                                    continue;
                                }
                                break;
                            case "list":
                                results = TaskUtil.checkLength(params, 3);
                                if(results.containsKey(1)){
                                    results = TaskUtil.checkLength(params, 2);
                                    if(results.containsKey(1)) {
                                        System.out.println(results.get(1)+" or 3");
                                        continue;
                                    }
                                }
                                break;
                            default:
                                System.out.println("you can only use command like this "+ Arrays.toString(commands));
                                continue;
                        }

                        //Check whether data associated with the specified ID exists.
                        if(params.get(1).equals("delete")|| params.get(1).equals("mark-in-progress")|| params.get(1).equals("mark-done")|| params.get(1).equals("update")){
                            results =TaskUtil.isExist(taskArrayList, params.get(2));
                            if(results.containsKey(1)){
                                System.out.println(results.get(1));
                                continue;
                            }
                        }
                        Task task;
                        //Perform the action associated with the specified command.
                        switch (params.get(1)) {
                            case "add":
                                int i =taskArrayList.size()+1;
                                taskArrayList.put(i,new Task(i, params.get(2),status[0], LocalDateTime.now(),LocalDateTime.now()));
                                JsonConvert.printMapToJson(taskArrayList);
                                System.out.println("Task added successfully (ID: "+i+")");
                                break;
                            case "update":
                                task = taskArrayList.get(Integer.parseInt(params.get(2)));
                                task.setDescription(params.get(3));
                                task.setUpdatedAt(LocalDateTime.now());
                                JsonConvert.printMapToJson(taskArrayList);
                                System.out.println("Task updated successfully (ID: "+ params.get(2) +")");
                                break;
                            case "delete":
                                taskArrayList.remove(Integer.parseInt(params.get(2)));
                                JsonConvert.printMapToJson(taskArrayList);
                                System.out.println("Task deleted successfully (ID: "+ params.get(2) +")");
                                break;
                            case "mark-in-progress":
                                task = taskArrayList.get(Integer.parseInt(params.get(2)));
                                task.setStatus(status[2]);
                                task.setUpdatedAt(LocalDateTime.now());
                                JsonConvert.printMapToJson(taskArrayList);
                                System.out.println("Task updated successfully (ID: "+ params.get(2) +")");
                                break;
                            case "mark-done":
                                task = taskArrayList.get(Integer.parseInt(params.get(2)));
                                task.setStatus(status[1]);
                                task.setUpdatedAt(LocalDateTime.now());
                                JsonConvert.printMapToJson(taskArrayList);
                                System.out.println("Task updated successfully (ID: "+ params.get(2) +")");
                                break;
                            case "list":
                                if(params.size()==2){
                                        TaskUtil.printMap(taskArrayList,"");
                                }else {
                                    TaskUtil.printMap(taskArrayList, params.get(2));
                                }
                                break;
                            default:
                                System.out.println("you can only use command like this "+ Arrays.toString(commands));
                        }
                    }
            }
        }
    }

}