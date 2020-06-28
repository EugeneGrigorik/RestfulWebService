package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.model.History;

@Service
public interface HistoryService {
	
	History addHistory(String fieldName, String oldValue, String newValue);
	
	List<History> showHistory();
	
	void deleteHistory();

}
