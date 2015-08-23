package com.roman.app.SpringJDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
public class GuitarsJDBC implements GuitarsDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcObject;
	private GuitarEventPublisher publisher;
	
	public void init(){
		
			
		create();
		
	}
	public void setDataSource(DataSource src) {
		// TODO Auto-generated method stub
		dataSource=src;
		jdbcObject=new JdbcTemplate(dataSource);
	}
	public void setPublisher(GuitarEventPublisher pb) {
		publisher=pb;
	}
	public void create() {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE  if not exists Guitars(ID INT AUTO_INCREMENT NOT NULL UNIQUE,"
				+ "name CHAR(25) NOT NULL,country CHAR(25) NOT NULL,year INT NOT NULL,"
				+ "count INT NOT NULL,PRIMARY KEY(ID));";
		jdbcObject.update(query);	
		publisher.publishDataChangedEvent(getList());
	}
	public void clear() {
		String query="DELETE FROM Guitars;";
		jdbcObject.update(query);
		publisher.publishDataChangedEvent(getList());
	}
	public void insert(Guitar g) {
		// TODO Auto-generated method stub
		String query="INSERT INTO Guitars(ID,name,country,year,count)VALUES(?,?,?,?,?);";
		
		jdbcObject.update(query,g.getID(), g.getName(),g.getCountry(),g.getYear(),g.getCount());
		publisher.publishDataChangedEvent(getList());
		
	}
	public void edit(int id, String column, String value) {
		// TODO Auto-generated method stub
		String query="UPDATE Guitars SET "+column+" = ? WHERE ID = ?;";
		jdbcObject.update(query,value,id);
		publisher.publishDataChangedEvent(getList());
	}
	public Guitar getGuitar(int id) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM Guitars WHERE ID = ?;";
		Guitar g = jdbcObject.queryForObject(query,new Object[]{id},new GuitarMapper());
		return g;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Guitars WHERE ID= ?;";
		jdbcObject.update(query,id);
		publisher.publishDataChangedEvent(getList());
	}

	public List<Guitar> getList() {
		// TODO Auto-generated method stub
		String query="SELECT * FROM Guitars;";
		List<Guitar> guitars=jdbcObject.query(query,new GuitarMapper());
		return guitars;
	}
	

}
