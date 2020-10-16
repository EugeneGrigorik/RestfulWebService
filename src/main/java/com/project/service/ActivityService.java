package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.model.Activity;

@Service
public interface ActivityService {
	
	Activity createActivity(Activity activity);
	
	Activity updateActivity(String id, String title, String summary, String description, String info);

	void deleteActivity(String id);
	
	Activity getActivity(String id);
	
	List<Activity> getAllActivity();
	
	void deleteAllActivity();
}
