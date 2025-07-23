package org.example.util;

import org.example.bean.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonConvert {
    public static void printMapToJson(Map<Integer, Task> maps){
        StringBuilder jsonString=new StringBuilder();
        if(maps.size()>0){
            for (Map.Entry<Integer,Task> entry : maps.entrySet()){
                Task task =entry.getValue();
                if(!jsonString.isEmpty()){
                    jsonString.append(",\n");
                }
                jsonString.append("{\n");
                jsonString.append("\"id\":\""+task.getId()+"\",\n");
                jsonString.append("\"description\":\""+task.getDescription()+"\",\n");
                jsonString.append("\"status\":\""+task.getStatus()+"\",\n");
                jsonString.append("\"createdAt\":\""+task.getCreatedAt()+"\",\n");
                jsonString.append("\"updatedAt\":\""+task.getUpdatedAt()+"\"\n");
                jsonString.append("}");
            }
            jsonString.insert(0,"[");
            jsonString.append("]");
        }


        try (FileWriter writer = new FileWriter("JsonData/task.json")) {
            writer.write(jsonString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Task> printJsonToMap(){
        Map<Integer, Task> map=new HashMap<>();
        try {

            List<String> lines = Files.readAllLines(Paths.get("JsonData/task.json"));
            Task task=new Task();;
            for (String line : lines) {
                if(line.contains("{")){
                    task=new Task();
                }else if(line.contains(":")){
                    line=line.replaceAll("\"","");
                    line=line.replaceAll(",","");
                    String[] temp=line.split(":",2);
                    String filedName=temp[0];
                    String filedValue=temp[1];
                    Field field=Task.class.getDeclaredField(filedName);
                    field.setAccessible(true);
                    if(field.getType()==int.class){
                        field.set(task,Integer.parseInt(filedValue));
                    }else if(field.getType()== LocalDateTime.class){
                        field.set(task,LocalDateTime.parse(filedValue));
                    }else {
                        field.set(task,filedValue);
                    }
                }else if(line.contains("}")){
                    map.put(task.getId(),task);
                }
            }
        } catch (IOException | NoSuchFieldException e) {
            return map;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return map;
    }
}
