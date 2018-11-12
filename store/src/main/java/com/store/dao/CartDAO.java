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
    
    public boolean insert_to_cart(Customer customer, Product  product, int itemId, String username)
    {
        if (customer.get_username() == username && product.get_id() == itemId)
        {
            String sql_count = "SELECT COUNT(*) FROM carts WHERE username = ?";
            String sql2      = "INSERT INTO carts (cartId, username, itemId, count) VALUES (?, ?, ?, ?);";
            int    rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { username }, Integer.class);
            int    cartId    = 0;

            if (rowCount > 0)
            {
                cartId = get_cart_id(username);
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

            String sql3 = "SELECT COUNT(*) FROM carts WHERE cartId = ? AND username = ? AND itemId = ?";
            String sql4 = "SELECT count FROM carts WHERE cartId = ? AND username = ? AND itemId = ?";
            rowCount    = this.jdbcTemplate.queryForObject(sql3, new Object[] { cartId, username, itemId }, Integer.class);

            if (rowCount == 0)
                this.jdbcTemplate.update(sql2, cartId, username, itemId, 1);
            else
            {
                int item_count = this.jdbcTemplate.queryForObject(sql4, new Object[] { cartId, username, itemId }, Integer.class);
                ++item_count;
                String sql5    = "UPDATE carts SET count =" + item_count +" WHERE cartId = ? AND username = ? AND itemId = ?";
                this.jdbcTemplate.update(sql5, cartId, username, itemId);
            }

            return true;
        }
        return false;
    }

    public int get_cart_id(String username)
    {
        String sql_count = "SELECT COUNT(*) FROM carts WHERE username = ?";
        String sql       = "SELECT DISTINCT cartId FROM carts WHERE username = ?";
        int    rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { username }, Integer.class);

        if (rowCount == 0) return 0;

        return Integer.parseInt(this.jdbcTemplate.queryForObject(sql, new Object[] { username }, String.class));    
    }
    
    public Collection<Product> get_cart_items(int cartId, ProductDAO productDAO)
    {
        Collection<Product> products = new ArrayList<Product>();

        String sql1 = "SELECT * FROM carts WHERE cartId = ?";
        String sql2 = "SELECT * FROM products JOIN (" + sql1 + ") cart_select ON products.itemId = cart_select.itemId";

        this.jdbcTemplate.query(
                sql2, new Object[] { cartId },
                (rs, rowNum) -> productDAO.select_product(rs.getInt("itemId"))
        ).forEach(product -> products.add(product));
        
        return products;
    }

    public boolean delete_item_from_cart(int cartId, int itemId)
    {
        String sql_count = "SELECT COUNT(*) FROM carts WHERE cartId = ? AND itemId = ?";
        int    rowCount  = this.jdbcTemplate.queryForObject(sql_count, new Object[] { cartId, itemId }, Integer.class);

        if (rowCount > 0)
        {
            String sql = "DELETE FROM carts WHERE cartId = ? AND itemId = ?";
            this.jdbcTemplate.update(sql, cartId, itemId);

            return true;
        }
        
        return false;
    }
    
    public int get_item_count(int cartId, int itemId)
    {
        String sql = "SELECT count FROM carts WHERE cartId = ? AND itemId = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new Object[] { cartId, itemId }, Integer.class);
    }

    public String get_username(int cartId)
    {
        String sql = "SELECT DISTINCT username FROM carts WHERE cartId = ?";

        return this.jdbcTemplate.queryForObject(sql, new Object[] { cartId }, String.class);
    }

    public boolean cart_exists(int cartId)
    {
        String sql = "SELECT COUNT(*) FROM carts WHERE cartId = ?";

        int rowCount = this.jdbcTemplate.queryForObject(sql, new Object[] { cartId }, Integer.class);

        if (rowCount == 0)
            return false;
        return true;
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



    //TODO: get_count(String column, String value)
    //TODO: get_count(String column, int value)
}