package com.roman.app.SpringJDBC;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class GuitarEventPublisher implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher publisher;
	public void setApplicationEventPublisher(ApplicationEventPublisher pb) {
		// TODO Auto-generated method stub
		publisher=pb;
	}
	public void publishDeleteGuitarEvent(int index) {
		DeleteGuitarEvent de=new DeleteGuitarEvent(this);
		de.setDeletedIndex(index);
		publisher.publishEvent(de);
	}
	public void publishNewGuitarEvent(Guitar g) {
		NewGuitarEvent ne=new NewGuitarEvent(this);
		ne.setGuitar(g);
		publisher.publishEvent(ne);
	}
	public void publishDataChangedEvent(List<Guitar> gs) {
		DataChangedEvent de = new DataChangedEvent(this);
		de.setGuitars(gs);
		publisher.publishEvent(de);
	}
	public void publishClearEvent() {
		publisher.publishEvent(new ClearEvent(this));
	}
	public void publishEditEvent(int indx,String col,String val) {
		EditEvent ee=new EditEvent(this);
		ee.setParams(indx, col, val);
		publisher.publishEvent(ee);
	}

}
