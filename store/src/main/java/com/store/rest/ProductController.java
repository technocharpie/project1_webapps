/*--------------------
	Web Apps UF Fall 2018, Dr. Brown
	copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport; 
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.ArrayList;
import org.json.JSONObject;



import com.store.dao.*;
import com.store.model.*;

@Controller
@Path("/items")
public class ProductController extends HttpServlet
{

	private ProductService productService = new ProductService();

	public void init(ServletConfig config) 
	{
		try{
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			  config.getServletContext());
		}catch(ServletException e){
		}
	}


		  
	@GET
	@Path("/{id}")
	public Response select_product(@PathParam("id") int id) 
	{
		Product product = productService.select_product(id);
		JSONObject json = new JSONObject(product.to_JSON());


		if (product != null && product.get_id() != 0)
			return Response
			.status(200)
		    .entity(json.toString()).build();
		

		return Response
		.status(404)
		.entity("Product ID: " + id + " not found.").build();
	}

}