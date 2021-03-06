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
public class ProductService 
{
	
	//@Autowired
	private ProductDAO productDAO = new ProductDAO();


	public Product select_product(int id) 
	{
		return productDAO.select_product(id);
	}

	public Collection<Product> select_all_product() 
	{
		return productDAO.select_all_product();
	}

	public Collection<Product> search_products(String keyword) 
	{
		return productDAO.search_products(keyword);
	}

}