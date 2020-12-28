package com.programming.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.programming.model.StatusUpdate;
import com.programming.service.StatusUpdateService;

@Controller
public class PageController {
	
	@Autowired
	private StatusUpdateService statusUpdateService;

	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView) {
		StatusUpdate statusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		modelAndView.setViewName("app.homepage");
		return modelAndView;
	}

	@RequestMapping("/about")
	public String about() {
		return "app.about";
	}

}
