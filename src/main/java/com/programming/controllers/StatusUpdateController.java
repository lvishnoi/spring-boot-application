package com.programming.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.programming.model.StatusUpdate;
import com.programming.service.StatusUpdateService;

@Controller
public class StatusUpdateController {

	@Autowired
	private StatusUpdateService statusUpdateService;

	@RequestMapping(value = "/viewstatus", method = RequestMethod.GET)
	public ModelAndView viewStatus(ModelAndView modelAndView,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

		Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);
		modelAndView.getModel().put("page", page);

		modelAndView.setViewName("app.viewstatus");

		return modelAndView;
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
			modelAndView.setViewName("redirect:/viewstatus");
		}

		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

		return modelAndView;
	}
}
