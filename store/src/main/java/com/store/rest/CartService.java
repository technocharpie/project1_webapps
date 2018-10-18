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
public class CartService 
{
	
	//@Autowired
	private CartDAO cartDAO = new CartDAO();
	
	public boolean insert_to_cart(int itemId, String username) 
	{
		return cartDAO.insert_to_cart(itemId, username);
		
	}

}