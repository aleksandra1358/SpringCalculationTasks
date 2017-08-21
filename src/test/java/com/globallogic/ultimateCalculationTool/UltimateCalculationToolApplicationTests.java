package com.globallogic.ultimateCalculationTool;

import com.globallogic.ultimateCalculationTool.executor.TaskCalculationService;
import com.globallogic.ultimateCalculationTool.executor.TaskCalculationServiceDBImpl;
import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataServiceDBImpl;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataService;
import com.globallogic.ultimateCalculationTool.taskService.TaskServiceDBImpl;
import com.globallogic.ultimateCalculationTool.taskService.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UltimateCalculationToolApplicationTests
{
    @Autowired
    private TaskDBImplRepository repository;

    private TaskService taskService;
    private TaskDataService taskDataService;
    private TaskCalculationService taskCalculationService;

    @Before
    public void before()
    {
        taskService = new TaskServiceDBImpl(repository);
        taskDataService = new TaskDataServiceDBImpl(repository);
        taskCalculationService = new TaskCalculationServiceDBImpl(repository);
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
    public void test_checkDataId()
    {
        Task task = taskService.createTask("foo");
        TaskData taskData = taskDataService.createTaskData(Arrays.asList(1d, 2d, 3d), Operation.plus, task);
        assertNotNull(taskData);
    }

    @Test
    public void test_addTestData()
    {
        //TODO: factory design pattern - read !
        Task task = taskService.createTask("foo");
        TaskData taskData = taskDataService.createTaskData(Arrays.asList(1d, 2d, 3d), Operation.plus, task);
        assertNotNull(taskData);
//        assertTrue(taskData.getId() > 0); //todo null id
        TaskData foundData = taskService.getTaskById(task.getId()).getData();
        assertNotNull(foundData);
        assertEquals(taskData.getOperation(), foundData.getOperation());
        assertEquals(taskData.getValues(), foundData.getValues());
        assertNotNull(taskData);
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
        assertNotNull(foundedResult);
        assertEquals(result.getResult(), foundedResult.getResult());
    }

    @Test
    public void test_findResult_whenMissing()
    {
        Task task = taskService.createTask("foo");
        Optional<Result> result = taskCalculationService.findResult(task);
        assertFalse(result.isPresent());
    }
}
