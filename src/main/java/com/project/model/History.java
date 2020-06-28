package com.project.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "history")
public class History {
	
	public enum Type {
		COMPOSE,
		UPDATE
	}
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dateTime;
	
	private History.Type type;
	private Change change;

	public History(LocalDateTime dateTime, Type type, Change change) {
		super();
		this.dateTime = dateTime;
		this.type = type;
		this.change = change;
	}

	public History() {
		super();
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public History.Type getType() {
		return type;
	}

	public void setType(History.Type type) {
		this.type = type;
	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

}
