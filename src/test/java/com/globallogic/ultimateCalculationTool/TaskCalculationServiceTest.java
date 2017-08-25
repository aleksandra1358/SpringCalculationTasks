package com.globallogic.ultimateCalculationTool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.globallogic.ultimateCalculationTool.executor.TaskCalculationService;
import com.globallogic.ultimateCalculationTool.generators.TaskDeletor;
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

	@Autowired
	private TaskDeletor taskDeletor;

	@Before
	public void setUp() {
		taskDeletor.deleteAllTasks();
	}

	@Test
	public void executeData_withGeneratedData() {
		taskGenerator.generateTasks(3);
		List<TaskDBImpl> tasks = taskRepository.findAll();
		Result result1 = taskCalculationService.executeTask(taskService.getTaskById(tasks.get(0).getId()));
		Result result2 = taskCalculationService.executeTask(taskService.getTaskById(tasks.get(1).getId()));
		Result result3 = taskCalculationService.executeTask(taskService.getTaskById(tasks.get(2).getId()));
		assertNotNull(result1);
		assertNotNull(result2);
		assertNotNull(result3);
	}

	@Test
	public void executeData_getTaskById() {
		Task task1 = taskService.createTask("foo");
		taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task1);
		Task task2 = taskService.createTask("foo");
		taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task2);
		Task task3 = taskService.createTask("foo");
		taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task3);
		assertEquals(3, taskRepository.count());
		taskRepository.findAll().forEach(t -> assertNotNull(t));
		assertNotNull(taskRepository.findById(1L));
		assertNotNull(taskService.getTaskById(1L));
		taskRepository.findAll().forEach(t -> assertNotNull(taskService.getTaskById(t.getId())));
		Result result1 = taskCalculationService.executeTask(taskService.getTaskById(task1.getId()));
		Result result2 = taskCalculationService.executeTask(taskService.getTaskById(task2.getId()));
		Result result3 = taskCalculationService.executeTask(taskService.getTaskById(task3.getId()));
		assertEquals(11d, result1.getResult(), 0.0);
		assertEquals(11d, result2.getResult(), 0.0);
		assertEquals(11d, result3.getResult(), 0.0);

	}

}
