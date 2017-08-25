package com.globallogic.ultimateCalculationTool;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.globallogic.ultimateCalculationTool.generators.TaskDataGenerator;
import com.globallogic.ultimateCalculationTool.generators.TaskDeletor;
import com.globallogic.ultimateCalculationTool.generators.TaskGenerator;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataService;
import com.globallogic.ultimateCalculationTool.taskService.TaskServiceDBImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskGeneratorsTest {

	@Autowired
	private TaskDataGenerator taskDataGenerator;

	@Autowired
	private TaskGenerator taskGenerator;

	@Autowired
	private TaskServiceDBImpl taskService;

	@Autowired
	private TaskDBImplRepository taskRepository;

	@Autowired
	private TaskDeletor taskDeletor;

	@Autowired
	private TaskDataService taskDataService;

	@Before
	public void setUp() {
		taskDeletor.deleteAllTasks();
	}

	@Test
	public void generateTaskData_OperationNotNull() {
		Task task = taskService.createTask("aaa");
		TaskData taskData = taskDataGenerator.generateTaskData(task);
		Assert.assertThat(taskData.getOperation(), Matchers.notNullValue());

	}

	@Test
	public void generateTaskData_ValuesNotNull() {
		Task task = taskService.createTask("aaa");
		TaskData taskData = taskDataGenerator.generateTaskData(task);
		Assert.assertThat(taskData.getValues(), Matchers.notNullValue());
	}

	@Test
	public void generateTask_WithFiveParameter() {
		long ammount = taskRepository.count();
		taskGenerator.generateTasks(5);
		Assert.assertEquals(ammount + 5, taskRepository.count());
	}

	@Test
	public void deleteAllTasks_check() {
		Task task1 = taskService.createTask("foo");
		taskDataService.createTaskData(Arrays.asList(6d, 2d, 3d), Operation.plus, task1);
		taskGenerator.generateTasks(5);
		taskDeletor.deleteAllTasks();
		Assert.assertEquals(0, taskRepository.count());
	}

}
