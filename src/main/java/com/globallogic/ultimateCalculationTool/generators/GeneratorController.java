package com.globallogic.ultimateCalculationTool.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {

	@Autowired
	TaskDeletor taskDeletor;

	@Autowired
	private TaskGenerator taskGenerator;

	public void GeneratorController() {
		taskDeletor.deleteAllTasks();
	}

	@PostMapping("generate")
	public void generateTasks(@RequestParam(value = "ammount", defaultValue = "0") int ammount) {
		taskGenerator.generateTasks(ammount);
	}

}
