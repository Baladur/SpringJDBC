package com.roman.app.SpringJDBC;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.context.ApplicationListener;
import org.swixml.*;
public class View {
	SwingEngine main,add,edit;
	private GuitarEventPublisher publisher;
	private List<Guitar> guitars;
	private int lastID;
	public JList<String> list;
	//private DefaultListModel listModel;
	public JTextField nameTf,countryTf,yearTf,countTf,nameEd,countryEd,yearEd,countEd;
	public JButton addBtn,clearBtn,saveBtn,deleteBtn;
	public Action toadd=new AbstractAction() {
		public void actionPerformed(ActionEvent ae) {
			if (!edit.getRootComponent().isVisible())		
				add.getRootComponent().setVisible(true);		
		}
	};
	public Action toedit=new AbstractAction() {
		public void actionPerformed(ActionEvent ae) {
			if (!add.getRootComponent().isVisible() && list.getSelectedIndex()>=0)
			{
				edit.getRootComponent().setVisible(true);
				Guitar selected=guitars.get(list.getSelectedIndex());
				nameEd.setText(selected.getName());
				countryEd.setText(selected.getCountry());
				yearEd.setText(new String().valueOf(selected.getYear()));
				countEd.setText(new String().valueOf(selected.getCount()));
			}
		}
	};
	public Action clear=new AbstractAction() {
		public void actionPerformed(ActionEvent ae) {
			publisher.publishClearEvent();
		}
	};
	
	public class DataChangedEventListener implements ApplicationListener<DataChangedEvent> {

		public void onApplicationEvent(DataChangedEvent de) {
			// TODO Auto-generated method stub
			setList(de.getGuitars());
			add.getRootComponent().setVisible(false);
			edit.getRootComponent().setVisible(false);
		}
		
	}
	View() {
		init();
	}
	public void setPublisher(GuitarEventPublisher pb) {
		publisher=pb;
	}
	public void setList(List<Guitar> gs) {
		guitars=gs;
		if (gs.size()>0)
			lastID=gs.get(gs.size()-1).getID();
		GuitarModel listModel=new GuitarModel();
		
		for (Guitar g:guitars) {
			String s=g.getName()+" "+g.getCountry()+" "+g.getYear()+" "+g.getCount()+" шт.";
			listModel.addElement(s);
		}
		list.setModel(listModel);
	}
	public List<Guitar> getList() {
		return guitars;
	}
	public void init() {	
		try {
			toadd.putValue(Action.NAME,"toadd");
			toedit.putValue(Action.NAME,"toedit");
			clear.putValue(Action.NAME, "clear");
			main=new SwingEngine(this);
			main.render("MainView.xml");
			main.getRootComponent().setVisible(true);
			add=new SwingEngine(this);
			add.render("AddFrame.xml");
			edit=new SwingEngine(this);
			edit.render("EditFrame.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					int a=Integer.parseInt(yearTf.getText());
					int b=Integer.parseInt(countTf.getText());
					String nm=nameTf.getText();
					String cntry=countryTf.getText();
					if (!nm.equals("") && !cntry.equals("")) {
						publisher.publishNewGuitarEvent(new Guitar(lastID+1,nm,cntry,a,b));
						nameTf.setText("");
						countryTf.setText("");
						yearTf.setText("");
						countTf.setText("");
					}
				} catch (NumberFormatException nfe) {
					
				}
				
			}
		});
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					int a=Integer.parseInt(yearEd.getText());
					int b=Integer.parseInt(countEd.getText());
					String nm=nameEd.getText();
					String cntry=countryEd.getText();
					if (!nm.equals("") && !cntry.equals("")) {
						Guitar selected=guitars.get(list.getSelectedIndex());
						if (!selected.getName().equals(nm))
							publisher.publishEditEvent(selected.getID(),"name",nm);
						if (!selected.getCountry().equals(cntry))
							publisher.publishEditEvent(selected.getID(), "country", cntry);
						if (selected.getYear()!=a)
							publisher.publishEditEvent(selected.getID(), "year", new String().valueOf(a));
						if (selected.getCount()!=b)
							publisher.publishEditEvent(selected.getID(), "count",new String().valueOf(b));
						edit.getRootComponent().setVisible(false);
					}
				} catch (NumberFormatException nfe) {
					
				}
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				publisher.publishDeleteGuitarEvent(guitars.get(list.getSelectedIndex()).getID());
				
			}
		});
		list.setSelectedIndex(0);
		
		
		
	}
}
