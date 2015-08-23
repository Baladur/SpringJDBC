package com.roman.app.SpringJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GuitarMapper implements RowMapper<Guitar> {

	public Guitar mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Guitar g = new Guitar(rs.getInt("ID"),rs.getString("name"),rs.getString("country"),rs.getInt("year"),rs.getInt("count"));
		return g;
	}

}
