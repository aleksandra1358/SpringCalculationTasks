package com.globallogic.ultimateCalculationTool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.globallogic.ultimateCalculationTool.executor.TaskCalculationService;
import com.globallogic.ultimateCalculationTool.generators.TaskGenerator;
import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;
import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataService;
import com.globallogic.ultimateCalculationTool.taskService.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskCalculationServiceTest {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskDataService taskDataService;

	@Autowired
	private TaskCalculationService taskCalculationService;

	@Autowired
	private TaskGenerator taskGenerator;

	@Autowired
	private TaskDBImplRepository taskRepository;

	@Test
	public void executeData_getTaskById() {
		Task task1 = taskService.createTask("foo");
		long id1 = task1.getId();
		taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task1);
		Task task2 = taskService.createTask("foo");
		long id2 = task2.getId();
		taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task2);
		Task task3 = taskService.createTask("foo");
		long id3 = task1.getId();
		taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task3);

		Result result1 = taskCalculationService.executeTask(taskService.getTaskById(id1));
		Result result2 = taskCalculationService.executeTask(taskService.getTaskById(id2));
		Result result3 = taskCalculationService.executeTask(taskService.getTaskById(id3));
		assertEquals(11d, result1.getResult(), 0.0);
		assertEquals(11d, result2.getResult(), 0.0);
		assertEquals(11d, result3.getResult(), 0.0);
	}

	@Test
	public void executeData_withGeneratedData() {
		taskGenerator.generateTasks(3);
		List<TaskDBImpl> tasks = taskRepository.findAll();
		long id1 = tasks.get(0).getId();
		long id2 = tasks.get(1).getId();
		long id3 = tasks.get(2).getId();
		Result result1 = taskCalculationService.executeTask(taskService.getTaskById(id1));
		Result result2 = taskCalculationService.executeTask(taskService.getTaskById(id2));
		Result result3 = taskCalculationService.executeTask(taskService.getTaskById(id3));
		assertNotNull(result1);
		assertNotNull(result2);
		assertNotNull(result3);
	}
}
