package com.globallogic.ultimateCalculationTool;

import java.util.Arrays;

import org.hamcrest.Matchers;
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
public class ScheduledExecutorServiceTest {

	@Autowired
	private ScheduledExecutorService scheduledService;

	@Autowired
	private TaskServiceDBImpl taskService;

	@Autowired
	private TaskDataServiceDBImpl taskDataService;

	@Test
	public void executeTasks_threads() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			Task task = taskService.createTask("abc");
			task.setData(taskDataService.createTaskData(Arrays.asList(1d, 2d, 3d), Operation.plus, task));
		}
		Thread.sleep(30000);
		Assert.assertThat(scheduledService.getAmmountOfTasks(), Matchers.lessThan(5));
	}

}
