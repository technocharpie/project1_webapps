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
public class ProductDAO
{
	private JdbcTemplate jdbcTemplate;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";


    //========== Constructors ==========//
    public ProductDAO() 
    {
		this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired	
    public ProductDAO(JdbcTemplate jdbcTemp) 
    {
        this.jdbcTemplate = jdbcTemp;
    }


    //========== INSERT / SELECT / UPDATE / DELETE ==========//
    public Product insert_product(Product product)
    {
        if (product != null)
        {
            String sql         = "INSERT INTO products (name, msrp, salePrice, upc, shortDescription, brandName, size, color, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            String name        = product.get_name();
			double msrp 	   = product.get_msrp();
			double salePrice   = product.get_salePrice();
			int    upc 		   = product.get_upc();
			String description = product.get_description();
			String brand 	   = product.get_brand();
			String size 	   = product.get_size();
			String color 	   = product.get_color();
			String gender 	   = product.get_gender();
            this.jdbcTemplate.update(sql, name, msrp, salePrice, upc, description, brand, size, color, gender);
        }

        return product;
    }

    public Product select_product(int id)
    {
        Product product   = new Product("", 0.0, 0.0, 0, "", "", "", "", "");
        String  sql_count = "SELECT COUNT(*) FROM products WHERE itemId = ?";
        int     rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { id }, Integer.class);

        if (rowCount > 0)
        {
            String sql1 = "SELECT name FROM products WHERE itemId = ?";
            String sql2 = "SELECT msrp FROM products WHERE itemId = ?";
            String sql3 = "SELECT salePrice FROM products WHERE itemId = ?";
            String sql4 = "SELECT upc FROM products WHERE itemId = ?";
            String sql5 = "SELECT shortDescription FROM products WHERE itemId = ?";
            String sql6 = "SELECT brandName FROM products WHERE itemId = ?";
            String sql7 = "SELECT size FROM products WHERE itemId = ?";
            String sql8 = "SELECT color FROM products WHERE itemId = ?";
            String sql9 = "SELECT gender FROM products WHERE itemId = ?";

            String name        = this.jdbcTemplate.queryForObject(sql1, new Object[] { id }, String.class);
            String msrp        = this.jdbcTemplate.queryForObject(sql2, new Object[] { id }, String.class);
            String salePrice   = this.jdbcTemplate.queryForObject(sql3, new Object[] { id }, String.class);
            String upc         = this.jdbcTemplate.queryForObject(sql4, new Object[] { id }, String.class);
            String description = this.jdbcTemplate.queryForObject(sql5, new Object[] { id }, String.class);
            String brand       = this.jdbcTemplate.queryForObject(sql6, new Object[] { id }, String.class);
            String size        = this.jdbcTemplate.queryForObject(sql7, new Object[] { id }, String.class);
            String color       = this.jdbcTemplate.queryForObject(sql8, new Object[] { id }, String.class);
            String gender      = this.jdbcTemplate.queryForObject(sql9, new Object[] { id }, String.class);

            product.set_id(id);
			product.set_name(name);
			product.set_msrp(Double.parseDouble(msrp));
			product.set_salePrice(Double.parseDouble(salePrice));
			product.set_upc(Integer.parseInt(upc));
			product.set_description(description);
			product.set_brand(brand);
			product.set_size(size);
			product.set_color(color);
			product.set_gender(gender);
		}
		
		return product;
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