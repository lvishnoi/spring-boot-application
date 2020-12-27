package com.programming.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.programming.model.StatusUpdate;
import com.programming.service.StatusUpdateService;

@Controller
public class PageController {

	@Autowired
	private StatusUpdateService statusUpdateService;

	@RequestMapping("/")
	public String home() {
		return "app.homepage";
	}

	@RequestMapping("/about")
	public String about() {
		return "app.about";
	}

	@RequestMapping(value = "/addstatus", method = RequestMethod.GET)
	public ModelAndView addStatus(ModelAndView modelAndView,
			@ModelAttribute("statusUpdate") StatusUpdate statusUpdate) {
		modelAndView.setViewName("app.addstatus");
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();

		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

		return modelAndView;
	}

	@RequestMapping(value = "/addstatus", method = RequestMethod.POST)
	public ModelAndView addStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {
		modelAndView.setViewName("app.addstatus");

		if (!result.hasErrors()) {
			statusUpdateService.save(statusUpdate);
			modelAndView.getModel().put("statusUpdate", new StatusUpdate());
		}

		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

		return modelAndView;
	}
}
