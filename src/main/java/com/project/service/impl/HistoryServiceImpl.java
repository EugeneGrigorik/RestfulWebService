package com.project.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Change;
import com.project.model.History;
import com.project.repository.HistoryRepository;
import com.project.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{

	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public History addHistory(String fieldName, String oldValue, String newValue) {
		History history = new History();
		history.setDateTime(LocalDateTime.now());
		if(oldValue == null) {
			history.setType(History.Type.COMPOSE);
		}else {
			history.setType(History.Type.UPDATE);
		}
		history.setChange(new Change(fieldName, oldValue, newValue));

		return historyRepository.save(history);
	}

	@Override
	public List<History> showHistory() {
		return historyRepository.findAll();
	}
	
	@Override
	public void deleteHistory() {
		historyRepository.deleteAll();
	}

}
