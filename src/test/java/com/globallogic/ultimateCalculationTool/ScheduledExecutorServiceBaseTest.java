package com.globallogic.ultimateCalculationTool;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.globallogic.ultimateCalculationTool.scheduleExecutor.ScheduledExecutorService;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataServiceDBImpl;
import com.globallogic.ultimateCalculationTool.taskService.TaskServiceDBImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduledExecutorServiceBaseTest {

	@Autowired
	private ScheduledExecutorService scheduledService;

	@Autowired
	private TaskServiceDBImpl taskService;

	@Autowired
	private TaskDataServiceDBImpl taskDataService;

	// testing method executeTask without @Scheduled
	@Test
	public void executeTask_emptyTaskList() throws InterruptedException {
		Task task = taskService.createTask("aaa");
		task.setData(taskDataService.createTaskData(Arrays.asList(1d, 2d, 3d), Operation.plus, task));
		scheduledService.loadTasksWithoutResult();
		scheduledService.executeTask();
		Assert.assertEquals(0, scheduledService.getAmmountOfTasks());
	}

}
