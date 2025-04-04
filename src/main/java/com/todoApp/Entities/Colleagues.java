package com.todoApp.Entities;

public class Colleagues {
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Colleagues() {
	}
	
	public Colleagues(long id, String name) {
		super();
		this.id=id;
		this.name= name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Colleagues[id="+id+", name="+name+"]";
	}
}
