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
public class CartDAO
{
	private JdbcTemplate jdbcTemplate;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";


    //========== Constructors ==========//
    public CartDAO() 
    {
		this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired	
    public CartDAO(JdbcTemplate jdbcTemp) 
    {
        this.jdbcTemplate = jdbcTemp;
    }


    //========== INSERT / SELECT / UPDATE / DELETE ==========//
    
    public boolean insert_to_cart(int itemId, String username)
    {
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO  productDAO  = new ProductDAO();

        Customer customer = customerDAO.select_customer(username);
        Product  product  = productDAO.select_product(itemId);

        if (customer.get_username() == username && product.get_id() == itemId)
        {
            String sql_count = "SELECT COUNT(*) FROM carts WHERE username = ?";
            String sql2      = "INSERT INTO carts (cartId, username, itemId, count) VALUES (?, ?, ?, ?);";
            int    rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { username }, Integer.class);
            int    cartId    = 0;

            if (rowCount > 0)
            {
                String sql1   = "SELECT cartId FROM carts WHERE username = ?";
                cartId        = Integer.parseInt(this.jdbcTemplate.queryForObject(sql1, new Object[] { username }, String.class));
            }
            else
            {
                Random rand = new Random();
                cartId      = rand.nextInt(999) + 1;

                sql_count = "SELECT COUNT(*) FROM carts WHERE cartId = ?";
                rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { cartId }, Integer.class);

                while (rowCount > 0)
                {
                    cartId   = rand.nextInt(999) + 1;
                    rowCount = this.jdbcTemplate.queryForObject(sql_count, new Object[] { cartId }, Integer.class);
                }
            }

            this.jdbcTemplate.update(sql2, cartId, username, itemId, 1);

            return true;
        }
        return false;
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