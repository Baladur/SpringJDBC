package com.roman.app.SpringJDBC;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roman.app.SpringJDBC.View.DataChangedEventListener;

public class App {
	private GuitarsJDBC database;
	private View view;
	public void setDatabase(GuitarsJDBC db) {
		database=db;
	}
	public GuitarsJDBC getDataBase() {
		return database;
	}
	public void setView(View v) {
		view=v;
	}
	public View getView() {
		return view;
	}
	public void init() {
		database.create();		
		view.setList(database.getList());
		
	}
	public class NewGuitarEventListener implements ApplicationListener<NewGuitarEvent> {
		public void onApplicationEvent(NewGuitarEvent ne) {
			database.insert(ne.getGuitar());
		}
	}
	public class DeleteGuitarEventListener implements ApplicationListener<DeleteGuitarEvent> {
		public void onApplicationEvent(DeleteGuitarEvent de) {
			database.delete(de.getDeletedIndex());
		}
	}
	public class ClearEventListener implements ApplicationListener<ClearEvent> {

		public void onApplicationEvent(ClearEvent ce) {
			// TODO Auto-generated method stub
			database.clear();
		}	
	}
	public class EditEventListener implements ApplicationListener<EditEvent> {
		public void onApplicationEvent(EditEvent ee) {
			database.edit(ee.getIndex(),ee.getColumn(),ee.getValue());
		}
	}
	
	
	
    public static void main( String[] args )
    {
    	
        ConfigurableApplicationContext cont = new ClassPathXmlApplicationContext("/Beans.xml");
        App app=(App)cont.getBean("app");
        cont.addApplicationListener(app.getView().new DataChangedEventListener());
        cont.addApplicationListener(app.new NewGuitarEventListener());
        cont.addApplicationListener(app.new DeleteGuitarEventListener());
        cont.addApplicationListener(app.new ClearEventListener());
        cont.addApplicationListener(app.new EditEventListener());
    }
}
