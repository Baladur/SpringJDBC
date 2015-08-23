package com.roman.app.SpringJDBC;

import java.util.List;

import javax.sql.DataSource;

public interface GuitarsDAO {
	public void setDataSource(DataSource src);
	public void create();
	public void clear();
	public void insert(Guitar g);
	public void edit(int id,String column,String value);
	public Guitar getGuitar(int id);
	public void delete(int id);
	public List<Guitar> getList();
}
