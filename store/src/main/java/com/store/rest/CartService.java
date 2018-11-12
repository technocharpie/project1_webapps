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
	private CartDAO     cartDAO     = new CartDAO();
	private ProductDAO  productDAO  = new ProductDAO();
	private CustomerDAO customerDAO = new CustomerDAO();
	
	public boolean insert_to_cart(int itemId, String username) 
	{
		Customer customer = customerDAO.select_customer(username);
        Product  product  = productDAO.select_product(itemId);

		return cartDAO.insert_to_cart(customer, product, itemId, username);
	}

	public int get_cart_id(String username) 
	{	
		return cartDAO.get_cart_id(username);
	}

	public Collection<Product> get_cart_items(int cartId) 
	{
		return cartDAO.get_cart_items(cartId, this.productDAO);
	}

	public boolean delete_item_from_cart(int cartId, int itemId) 
	{
		return cartDAO.delete_item_from_cart(cartId, itemId);
	}


}