/*--------------------
	Web Apps UF Fall 2018, Dr. Brown
	copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CustomerService 
{
	
	//@Autowired
	private CustomerDAO customerDAO = new CustomerDAO();
	
 
	public String getMsg(String msg) 
	{ 
		return "Hello : " + msg;
	}

	public Customer select_customer(String username) 
	{
		return customerDAO.select_customer(username);
	}

	public boolean insert_customer(String fname, String lname, String username, String email) 
	{
		return customerDAO.insert_customer(new Customer(fname, lname, username, email));
		
	}

	public boolean update_customer(String fname, String lname, String username, String email) 
	{
		return customerDAO.update_customer(new Customer(fname, lname, username, email));
		
	}

}