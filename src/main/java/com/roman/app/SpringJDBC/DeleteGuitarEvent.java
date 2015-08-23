package com.roman.app.SpringJDBC;

import org.springframework.context.ApplicationEvent;


public class DeleteGuitarEvent extends ApplicationEvent {
	private int deletedIndex;
	DeleteGuitarEvent(Object src) {
		super(src);
	}
	public void setDeletedIndex(int di) {
		deletedIndex=di;
	}
	public int getDeletedIndex() {
		return deletedIndex;
	}
}
