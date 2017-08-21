//package com.globallogic.ultimateCalculationTool;
//
//import com.globallogic.ultimateCalculationTool.result.Result;
//import com.globallogic.ultimateCalculationTool.result.ResultImpl;
//import com.globallogic.ultimateCalculationTool.task.Task;
//import com.globallogic.ultimateCalculationTool.task.oldImpl.TaskImpl;
//import com.globallogic.ultimateCalculationTool.taskData.TaskData;
//import com.globallogic.ultimateCalculationTool.taskData.oldImpl.TaskDataImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class InterfacesConfig
//{
//    @Bean
//    public Task task()
//    {
//        final TaskImpl task = new TaskImpl();
//        task.setId(1);
//        task.setDescription("Test");
//        task.setData(data());
//        task.setResult(result());
//        return task;
//    }
//
//    @Bean
//    public TaskData data()
//    {
//        final TaskDataImpl data = new TaskDataImpl();
//        final List<Integer> list = new ArrayList<>();
//        data.setId(1);
//        data.setNumbers(list);
//        data.setOperation(Operation.plus);
//        return data;
//    }
//
//    @Bean
//    public Result result()
//    {
//        ResultImpl result = new ResultImpl();
//        result.setResult(1);
//        return result;
//    }
//}
