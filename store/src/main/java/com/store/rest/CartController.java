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
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
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
@Path("/carts")
public class CartController extends HttpServlet
{

	private CartService cartService = new CartService();

	public void init(ServletConfig config) 
	{
		try{
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			  config.getServletContext());
		}catch(ServletException e){
		}
	}



	@POST
	public Response insert_to_cart(
		@QueryParam("productId") int itemId,
		@QueryParam("username") String username)
	{
		boolean worked = cartService.insert_to_cart(itemId, username);

		if (worked)
			return Response
			.status(200)
		    .entity("Item added to cart.").build();

		return Response
		.status(500)
	    .entity("Could not add new item to cart.").build();
	}

}