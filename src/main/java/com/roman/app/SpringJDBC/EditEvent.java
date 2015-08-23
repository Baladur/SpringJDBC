package com.roman.app.SpringJDBC;

import org.springframework.context.ApplicationEvent;

public class EditEvent extends ApplicationEvent {
	private int index;
	private String column;
	private String value;
	EditEvent(Object src) {
		super(src);
	}
	public void setParams(int indx,String col,String val) {
		index=indx;
		column=col;
		value=val;
	}
	public int getIndex() {
		return index;
	}
	public String getColumn() {
		return column;
	}
	public String getValue() {
		return value;
	}
}
