package com.globallogic.ultimateCalculationTool.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {

	@Autowired
	private TaskGenerator taskGenerator;

	@Autowired
	private TaskDeletor taskDeletor;

	@PostMapping("generate")
	public void generateTasks(@RequestParam(value = "ammount", defaultValue = "0") int ammount) {
		taskGenerator.generateTasks(ammount);
	}

}
