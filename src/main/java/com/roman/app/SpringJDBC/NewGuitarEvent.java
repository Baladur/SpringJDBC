package com.roman.app.SpringJDBC;

import org.springframework.context.ApplicationEvent;

public class NewGuitarEvent extends ApplicationEvent {
	private Guitar guitar;
	NewGuitarEvent(Object src) {
		super(src);
	}
	public Guitar getGuitar() {
		return guitar;
	}
	public void setGuitar(Guitar g) {
		guitar=g;
	}


}
