package com.project.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Activity;
import com.project.repository.ActivityRepository;
import com.project.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;
	private HistoryServiceImpl historyServiceImpl;

	@Autowired
	public ActivityServiceImpl(ActivityRepository activityRepository, HistoryServiceImpl historyServiceImpl) {
		this.activityRepository = activityRepository;
		this.historyServiceImpl = historyServiceImpl;
	}

	@Override
	public Activity createActivity(String title, String summary, String description, String info) {
		Activity activity = new Activity();

		historyServiceImpl.addHistory("title", activity.getTitle(), title);
		activity.setTitle(title);

		historyServiceImpl.addHistory("summary", activity.getSummary(), summary);
		activity.setSummary(summary);

		historyServiceImpl.addHistory("description", activity.getDescription(), description);
		activity.setDescription(description);

		historyServiceImpl.addHistory("info", activity.getInfo(), info);
		activity.setInfo(info);

		activity.setStartDateTime(LocalDateTime.now());
		activity.setEndDateTime(LocalDateTime.now());

		return activityRepository.save(activity);
	}

	@Override
	public Activity updateActivity(String id, String title, String summary, String description, String info) {
		Optional<Activity> activityId = activityRepository.findById(id);
		Activity activity = activityId.get();

		if(title != null && !title.equals(activity.getTitle())) {
			historyServiceImpl.addHistory("title", activity.getTitle(), title);
			activity.setTitle(title);	
		}

		if(summary != null && !summary.equals(activity.getSummary())) {
			historyServiceImpl.addHistory("summary", activity.getSummary(), summary);
			activity.setSummary(summary);			
		}

		if(description != null && !description.equals(activity.getDescription())) {
			historyServiceImpl.addHistory("description", activity.getDescription(), description);
			activity.setDescription(description);			
		}

		if(info != null && !info.equals(activity.getInfo())) {
			historyServiceImpl.addHistory("info", activity.getInfo(), info);
			activity.setInfo(info);			
		}

		activity.setEndDateTime(LocalDateTime.now());

		return activityRepository.save(activity);
	}

	@Override
	public void deleteActivity(String id) {
		activityRepository.deleteById(id);
	}

	@Override
	public Activity getActivity(String id) {
		Optional<Activity> activitydb = activityRepository.findById(id);
		Activity activity = activitydb.get();
		return activity;
	}

	@Override
	public List<Activity> getAllActivity() {
		return activityRepository.findAll();
	}
	
	@Override
	public void deleteAllActivity() {
		activityRepository.deleteAll();
	}

}
