package org.example.util;

import org.example.bean.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class TaskUtil {

    private TaskUtil(){

    }

    //检查输入的命令是否满足参数数量要求
    public static Map<Integer,String> checkLength(String[] params,int length){
        Integer resultKey=0;
        String message="";
        Map<Integer,String> results =new HashMap<>();


        //check params length
        if(params.length != length){
            resultKey=1;
            message="The number of input code parameters is incorrect, the correct length is "+length;
        }

        results.put(resultKey,message);
        return results;
    }

    //检查要修改的对象是否存在
    public static Map<Integer,String> isExist(Map<Integer,Task> arrayList, String id){
        Integer resultKey=1;
        String message="id "+ id +" does not exist";
        Map<Integer,String> results =new HashMap<>();
        try {
            int target=Integer.parseInt(id);
            if(arrayList.containsKey(target)){
                resultKey = 0;
                message="";
            }
        }catch (Exception e){
            message="The id must be of numeric type";
        }
        results.put(resultKey,message);
        return results;
    }

    //打印map
    public static void printMap(Map<Integer,Task> arrayList,String condition){

        if(!arrayList.isEmpty()) {
            if(condition.isEmpty()) {
                System.out.println("|-id-|-----status-----|------------------description------------------|------createdAt------|------updatedAt------|");
                for (Map.Entry<Integer, Task> entry : arrayList.entrySet()) {
                    Task task = entry.getValue();
                    System.out.println("|"+String.format("%-4s",String.valueOf(task.getId())) + "|" + String.format("%-16s",task.getStatus())
                            + "|" + String.format("%-47s", task.getDescription()) + "|" + String.format("%-21s",task.getCreatedAt())  + "|" +String.format("%-21s",task.getUpdatedAt()) +"|" );
                }
            }else {

                boolean flg=true;
                for (Map.Entry<Integer, Task> entry : arrayList.entrySet()) {
                    Task task = entry.getValue();
                    if(task.getStatus().equals(condition)){
                        if (flg){
                            System.out.println("|-id-|-----status-----|------------------description------------------|------createdAt------|------updatedAt------|");
                            flg=false;
                        }
                        System.out.println("|"+String.format("%-4s",String.valueOf(task.getId())) + "|" + String.format("%-16s",task.getStatus())
                                + "|" + String.format("%-47s", task.getDescription()) + "|" + String.format("%-21s",task.getCreatedAt())  + "|" +String.format("%-21s",task.getUpdatedAt()) +"|" );

                    }
                }
            }
        }else {
            System.out.println("there is no data");
        }
    }

}
