package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component("appDAO")
public class appDAO {

private JdbcTemplate jdbc;	
    
@Autowired	
public void setDataSource(DataSource dataSource) {
	this.jdbc = new JdbcTemplate(dataSource);
}
	public List<app> getOffers() {
               
		
		System.out.println("indao"+jdbc+"notjdbc");
		return jdbc.query("select * from customer ;", new RowMapper<app>() {

			public app mapRow(ResultSet rs, int rowNum) throws SQLException {
				app app1 = new app();

				app1.setTransaction_ID(rs.getInt("Transaction_ID"));
				app1.setCustomer_User_name(rs.getString("Customer_User_name"));
				app1.setTransaction_Type(rs.getString("Transaction_Type"));
				app1.setAccess_level(rs.getInt("Access_level"));

				return app1;
			}

		});
	}
	
	
	
	
}
