package com.todoApp.Entities;


import java.sql.Timestamp;

public class Tasks {
	private long id;
	private String task;
	private boolean completed;
	private Timestamp created_at;
	private String note;
	
	
	public Tasks() {};

	
	public Tasks(long id, String task, boolean completed, Timestamp created_at, String note) {
		super();
		this.id = id;
		this.task = task;
		this.completed = completed;
		this.created_at = created_at;
		this.note = note;
	}


	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	public long getId(){
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTask() {
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void markAtCompleted() {
		this.completed = true;
	}
	
	public Timestamp getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "tasks [id=" + id + ", task=" + task + ", completed=" + completed + ", created_at=" + created_at
				+ ", note=" + note + "]";
	}
	

	
}
