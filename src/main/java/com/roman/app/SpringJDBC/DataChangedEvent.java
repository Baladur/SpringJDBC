package com.roman.app.SpringJDBC;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class DataChangedEvent extends ApplicationEvent {
	private List<Guitar> guitars;
	DataChangedEvent(Object src) {
		super(src);
	}
	public void setGuitars(List<Guitar> gs) {
		guitars=gs;
	}
	public List<Guitar> getGuitars() {
		return guitars;
	}
}
