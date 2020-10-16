package com.project.controller;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Activity;
import com.project.model.History;
import com.project.service.impl.ActivityServiceImpl;
import com.project.service.impl.HistoryServiceImpl;

@RestController
@Validated
public class Controller {

	private ActivityServiceImpl activityServiceImpl;
	private HistoryServiceImpl historyServiceImpl;

	@Autowired
	public Controller (ActivityServiceImpl activityServiceImpl, HistoryServiceImpl historyServiceImpl) {
		this.activityServiceImpl = activityServiceImpl;
		this.historyServiceImpl = historyServiceImpl;
	}

	@PostMapping("/create-activity")
	public Activity createActivity(@RequestBody @Validated Activity activity) {
		return activityServiceImpl.createActivity(activity);
	}

	@PutMapping("/update-activity/{id}")
	public Activity updateActivity(
			@PathVariable(value = "id", required = true) String id,
			@RequestParam(value = "title", required = false) @Length(min = 1, max = 100, message = "Title is mandatory and must not exceed 100 characters") String title,
			@RequestParam(value = "summary", required = false) @Length(min = 1, max = 25, message = "Summary is mandatory and must not exceed 25 characters") String summary,
			@RequestParam(value = "description", required = false) @Length(min = 1, max = 200, message = "Desctiption is mandatory and must not exceed 200 characters") String description,
			@RequestParam(value = "info", required = false) String info) {
		return activityServiceImpl.updateActivity(id, title, summary, description, info);
	}
	
	@DeleteMapping(value = "/delete-activity/{id}")
	public void deleteActivity(@PathVariable (name = "id") String id) {
		activityServiceImpl.deleteActivity(id);
	}
	
	@GetMapping(value = "/show-activity/{id}")
	public Activity getActivity(@PathVariable (name = "id") String id) {
		return activityServiceImpl.getActivity(id);
	}

	@GetMapping("/show-all-activity")
	public List<Activity> getAllActivity(){
		return activityServiceImpl.getAllActivity();
	}
	
	@DeleteMapping("/delete-all-activity")
	public void deleteAllActivity(){
		activityServiceImpl.deleteAllActivity();
	}

	@GetMapping("/show-history")
	public List<History> showHistory(){
		return historyServiceImpl.showHistory();
	}
	
	@DeleteMapping("/delete-history")
	public void deleteHistory(){
		historyServiceImpl.deleteHistory();
	}

}
