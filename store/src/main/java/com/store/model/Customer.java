/*--------------------
	Web Apps UF Fall 2018, Dr. Brown
	copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

package com.store.model;

import java.util.Collection;

public class Customer
{
	private int    			  id;
	private String 			  f_name;
	private String 			  l_name;
	private String 			  username;
	private String 		      email;
	private Cart   			  cart;
	private Collection<Order> orders;


	//========== Constructors ==========//
	public Customer(String f_name, String l_name, String username, String email)
	{
		this.f_name   = f_name;
		this.l_name   = l_name;
		this.username = username;
		this.email 	  = email;
	}

	public Customer(Customer customer)
	{
		this.id 	  = customer.id;
		this.f_name   = customer.f_name;
		this.l_name   = customer.l_name;
		this.username = customer.username;
		this.email 	  = customer.email;
		this.cart     = customer.cart;
		this.orders   = customer.orders;
	}


	//========== GETers ==========//
	public int get_id()
	{
		return id;
	}

	public String get_f_name()
	{
		return f_name;
	}

	public String get_l_name()
	{
		return l_name;
	}

	public String get_username()
	{
		return username;
	}


	public String get_email()
	{
		return email;
	}

	public Cart get_cart()
	{
		return cart;
	}

	public Collection<Order> get_orders()
	{
		return orders;
	}


	//========== SETers ==========//
	public void set_id(int id)
	{
		this.id = id;
	}

	public void set_f_name(String f_name)
	{
		this.f_name = f_name;
	}

	public void set_l_name(String l_name)
	{
		this.l_name = l_name;
	}

	public void set_username(String username)
	{
		this.username = username;
	}

	public void set_email(String email)
	{
		this.email = email;
	}

	public void set_cart(Cart cart)
	{
		this.cart = cart;
	}

	public void set_orders(Collection<Order> orders)
	{
		this.orders = orders;
	}


	//========== Misc. ==========//
	@Override
    public String toString() 
    {
        return String.format("Customer [id = %d, f_name = '%s', l_name = '%s', username = '%s', email = '%s', cart = '%s', orders = '%s']", 
        	id, f_name, l_name, username, email, cart, orders);
    }

    public String to_JSON()
    {
    	return String.format("{\"id\":%d,\"f_name\":\"%s\",\"l_name\":\"%s\",\"username\":\"%s\",\"email\":\"%s\",\"cart\":\"%s\",\"orders\":\"%s\"}", 
        	id, f_name, l_name, username, email, cart, orders);
    }
}