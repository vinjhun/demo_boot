package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping(value="/")
	@Description("when the model is put within the arguments, add attribute is not need. so intelligence?")
	@Autowired
	private String layout() {
		return "index";
	}
}
