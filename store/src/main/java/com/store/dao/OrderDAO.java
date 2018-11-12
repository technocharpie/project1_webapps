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
import java.util.Random;

@Repository
public class OrderDAO
{
	private JdbcTemplate jdbcTemplate;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    //========== Constructors ==========//
    public OrderDAO() 
    {
		this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired	
    public OrderDAO(JdbcTemplate jdbcTemp) 
    {
        this.jdbcTemplate = jdbcTemp;
    }



    //========== INSERT / SELECT / UPDATE / DELETE ==========//
    
    public void insert_to_order(int orderId, String username, int itemId, int count)
    {
        String sql = "INSERT INTO orders (orderId, username, itemId, count) VALUES (?, ?, ?, ?);";

        this.jdbcTemplate.update(sql, orderId, username, itemId, count);
    }

    public Collection<Customer> get_customers(int itemId, CustomerDAO customerDAO)
    {
        Collection<Customer> customers = new ArrayList<Customer>();;
        String              sql        = "SELECT COUNT(*) FROM orders WHERE itemId = ?";

        if ( (this.jdbcTemplate.queryForObject(sql, new Object[] { itemId }, Integer.class)) == 0)
            return null;

        sql = "SELECT DISTINCT username FROM orders WHERE itemId = ?";

        this.jdbcTemplate.query(
                sql, new Object[] { itemId },
                (rs, rowNum) -> customerDAO.select_customer(rs.getString("username"))
        ).forEach(customer -> customers.add(customer));
        
        return customers;
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

    public int create_order_id()
    {
        Random rand    = new Random();
        int    orderId = rand.nextInt(999) + 1;

        String sql_count = "SELECT COUNT(*) FROM orders WHERE orderId = ?";
        int    rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { orderId }, Integer.class);

        while (rowCount > 0)
        {
            orderId  = rand.nextInt(999) + 1;
            rowCount = this.jdbcTemplate.queryForObject(sql_count, new Object[] { orderId }, Integer.class);
        }

        return orderId;
    }
    //TODO: get_count(String column, String value)
    //TODO: get_count(String column, int value)
}