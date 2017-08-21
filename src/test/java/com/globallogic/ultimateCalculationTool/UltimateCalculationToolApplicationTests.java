package com.globallogic.ultimateCalculationTool;

import com.globallogic.ultimateCalculationTool.executor.TaskCalculationService;
import com.globallogic.ultimateCalculationTool.executor.TaskCalculationServiceImpl;
import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataService;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataServiceImpl;
import com.globallogic.ultimateCalculationTool.taskService.TaskService;
import com.globallogic.ultimateCalculationTool.taskService.TaskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UltimateCalculationToolApplicationTests
{
    private TaskService taskService;
    private TaskDataService taskDataService;
    private TaskCalculationService taskCalculationService;

    @Before
    public void before()
    {
        taskService = new TaskServiceImpl();
        taskDataService = new TaskDataServiceImpl();
        taskCalculationService = new TaskCalculationServiceImpl();
    }

    @Test
    public void test_createTask()
    {
        Task task = taskService.createTask("foo");
        assertNotNull(task);
        assertTrue(task.getId() > 0);
        //1. create a task
        //2. Add operation and values to a task
        //3. Execute the operation and get the result
    }

    @Test
    public void test_findTask()
    {
        Task task = taskService.createTask("foo");
        Task found = taskService.getTaskById(task.getId());
        assertEquals(task.getId(), found.getId());
        assertEquals(task.getDescription(), found.getDescription());
    }

    @Test
    public void test_addTestData()
    {
        //TODO: factory design pattern - read !
        Task task = taskService.createTask("foo");
        TaskData taskData = taskDataService.createTaskData(Arrays.asList(1d, 2d, 3d), Operation.plus, task);
        assertNotNull(taskData);
        assertTrue(taskData.getId() > 0);
        TaskData foundData = taskService.getTaskById(task.getId()).getData();
        assertEquals(taskData.getOperation(), foundData.getOperation());
        assertEquals(taskData.getValues(), foundData.getValues());
    }

    @Test
    public void test_executeData()
    {
        //1. take task
        //2. gets data -> gets values and operation (task.getData.getValues)
        //3. execute data and store the result in task field.
        Task task = taskService.createTask("foo");
        taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task);
        Result result = taskCalculationService.executeTask(task);
        assertNotNull(result);
//        task.setResult(result);
        assertEquals(11d, result.getResult(), 0.0);
        Result foundedResult = taskService.getTaskById(task.getId()).getResult();
        assertEquals(result, foundedResult);
    }

    @Test
    public void test_findResult_whenMissing()
    {
        Task task = taskService.createTask("foo");
        Optional<Result> result = taskCalculationService.findResult(task);
        assertFalse(result.isPresent());
    }

//    @Test
//    public void Get_PassedConstNumber_IdSameAsPassedNumber()
//    {
//        final int testId = 1;
////        final TaskImpl task = new TaskImpl();
////        task.setId(testId);
//        assertEquals(testId, task.getId());
//    }
//
//    @Test
//    public void getTaskDescription()
//    {
//        final String testDesc = "Test";
////        final TaskImpl task = new TaskImpl();
////        task.setDescription(testDesc);
//        assertEquals(testDesc, task.getDescription());
//    }
//
//    @Test
//    public void getTaskResult()
//    {
////        final ResultImpl result = new ResultImpl();
////        final TaskImpl task = new TaskImpl();
////        task.setResult(result);
//        assertEquals(result, task.getResult());
//    }
//
//    @Test
//    public void getTaskData()
//    {
////        final TaskDataImpl data = new TaskDataImpl();
////        final TaskImpl task = new TaskImpl();
////        task.setData(data);
//        assertEquals(data, task.getData());
//    }
//
//    @Test
//    public void getTaskDataId()
//    {
//        final int testId = 1;
////        final TaskDataImpl data = new TaskDataImpl();
////        data.setId(testId);
//        assertEquals(testId, data.getId());
//    }
//
//    @Test
//    public void setTaskDataNumbers()
//    {
//        final TaskDataImpl data = new TaskDataImpl();
//        final List<Integer> integers = new ArrayList<>();
//        data.setNumbers(integers);
//        assertEquals(integers, data.getNumbers());
//    }
//
//    @Test
//    public void setTaskDataOperation()
//    {
//        final TaskDataImpl data = new TaskDataImpl();
//        final Operation operation = Operation.plus;
//        data.setOperation(operation);
//        assertEquals(operation, data.getOperation());
//    }
//
//    @Test
//    public void getResult()
//    {
//        final int testResult = 1;
//        final ResultImpl result = new ResultImpl();
//        result.setResult(testResult);
//        assertEquals(testResult, result.getResult());
//    }
//
//    @Test
//    public void Add_NewTaskAddedToServiceList_OneItemOnServiceList()
//    {
//        final TaskServiceImpl service = new TaskServiceImpl();
//        assertEquals(0, service.taskNumber());
//        service.addTask(new TaskImpl());
//        assertEquals(1, service.taskNumber());
//    }
//
//    @Test
//    public void getTaskById()
//    {
//        final TaskServiceImpl service = new TaskServiceImpl();
//        final int testId = 1;
//        final TaskImpl task = new TaskImpl();
//        task.setId(testId);
//        service.addTask(task);
//        assertEquals(1, service.taskNumber());
//        assertEquals(task, service.getTaskById(testId));
//    }
}
