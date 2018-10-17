/*--------------------
    Web Apps UF Fall 2018, Dr. Brown
    copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

package com.store.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

@Repository
public class CustomerDAO
{
	private JdbcTemplate jdbcTemplate;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";


    //========== Constructors ==========//
    public CustomerDAO() 
    {
		this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired	
    public CustomerDAO(JdbcTemplate jdbcTemp) 
    {
        this.jdbcTemplate = jdbcTemp;
    }


    //========== INSERT / SELECT / UPDATE / DELETE ==========//
    public boolean insert_customer(Customer customer)
    {
        if (customer != null)
        {
            String sql_count = "SELECT COUNT(*) FROM customers WHERE username = ?";
            int    rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { customer.get_username() }, Integer.class);

            if (rowCount == 0)
            {
                String sql      = "INSERT INTO customers (fname, lname, username, email) VALUES (?, ?, ?, ?);";
                String fname    = customer.get_f_name();
    			String lname	= customer.get_l_name();
    			String username = customer.get_username();
    			String email 	= customer.get_email();
                this.jdbcTemplate.update(sql, fname, lname, username, email);
                return true;
            }
        }

        return false;
    }

    public Customer select_customer(String username)
    {
        Customer customer  = new Customer("", "", "", "");
        String   sql_count = "SELECT COUNT(*) FROM customers WHERE username = ?";
        int      rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { username }, Integer.class);

        if (rowCount > 0)
        {
            String sql1 = "SELECT fname FROM customers WHERE username = ?";
            String sql2 = "SELECT lname FROM customers WHERE username = ?";
            String sql3 = "SELECT id FROM customers WHERE username = ?";
            String sql4 = "SELECT email FROM customers WHERE username = ?";

            String fname = this.jdbcTemplate.queryForObject(sql1, new Object[] { username }, String.class);
            String lname = this.jdbcTemplate.queryForObject(sql2, new Object[] { username }, String.class);
            String id    = this.jdbcTemplate.queryForObject(sql3, new Object[] { username }, String.class);
            String email = this.jdbcTemplate.queryForObject(sql4, new Object[] { username }, String.class);

            customer.set_id(Integer.parseInt(id));
			customer.set_f_name(fname);
            customer.set_l_name(lname);
            customer.set_username(username);
            customer.set_email(email);
		}
		
		return customer;
	}



    //========== Misc. ==========//
    public DriverManagerDataSource getDataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName(driverClassName);
		  dataSource.setUrl(url);
		  dataSource.setUsername(dbUsername);
		  dataSource.setPassword(dbPassword);
		  return dataSource;

    }
}