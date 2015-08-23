package com.roman.app.SpringJDBC;

import org.springframework.context.ApplicationEvent;

public class ClearEvent extends ApplicationEvent {
	ClearEvent(Object src) {
		super(src);
	}
}
