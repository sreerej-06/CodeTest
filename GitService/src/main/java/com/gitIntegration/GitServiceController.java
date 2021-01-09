package com.gitIntegration;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dataobject.Persons;


@RestController
public class GitServiceController {

	@PostMapping("/greetings")
	public Persons greeting(Model model) {
		Persons S= new Persons(3, "RamHanuman");
		
		return S;
	}
}
