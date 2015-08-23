package com.roman.app.SpringJDBC;

public class Guitar {
	private int ID;
	private String name;
	private String country;
	private int year;
	private int count;
	Guitar() {}
	Guitar(int id,String nm,String cntry,int y,int cnt) {
		ID=id;
		name=nm;
		country=cntry;
		year=y;
		count=cnt;
	}
	public void setID(int id) {
		ID=id;
	}
	public int getID() {
		return ID;
	}
	public void setName(String nm) {
		name=nm;
	}
	public String getName() {
		return name;
	}
	public void setCountry(String cntry) {
		country=cntry;
	}
	public String getCountry() {
		return country;
	}
	public void setYear(int y) {
		year=y;
	}
	public int getYear() {
		return year;
	}
	public void setCount(int cnt) {
		count=cnt;
	}
	public int getCount() {
		return count;
	}
	public void buy() {
		if (count>0)
			count--;
	}
}
