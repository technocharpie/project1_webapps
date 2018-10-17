/*--------------------
	Web Apps UF Fall 2018, Dr. Brown
	copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

package com.store.model;

import java.util.Collection;

public class Order
{
	private int    			    id;
	private String 			    username;
	private Collection<Product> products;


	//========== Constructors ==========//
	public Order(String username)
	{
		this.username = username;
	}

	public Order(Order order)
	{
		this.id 	  = order.id;
		this.username = order.username;
		this.products = order.products;
	}


	//========== GETers ==========//
	public int get_id()
	{
		return id;
	}

	public String get_username()
	{
		return username;
	}

	public Collection<Product> get_products()
	{
		return products;
	}


	//========== SETers ==========//
	public void set_id(int id)
	{
		this.id = id;
	}

	public void set_username(String username)
	{
		this.username = username;
	}

	public void get_products(Collection<Product> products)
	{
		this.products = products;
	}
	

	//========== Misc. ==========//
	@Override
    public String toString() 
    {
        return String.format("Order [id = %d, username = '%s', products = '%s']", 
        	id, username, products);
    }
}