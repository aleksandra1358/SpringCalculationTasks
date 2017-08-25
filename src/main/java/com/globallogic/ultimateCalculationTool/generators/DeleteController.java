package com.globallogic.ultimateCalculationTool.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {

	@Autowired
	private TaskDeletor taskDeletor;

	@PostMapping("delete")
	public void deleteAllTasks() {
		taskDeletor.deleteAllTasks();
	}

}
