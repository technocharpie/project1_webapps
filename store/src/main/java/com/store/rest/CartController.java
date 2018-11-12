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
import org.json.JSONArray;


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
		    	.entity("Item added to cart.")
		    	.build();

		return Response
			.status(500)
	    	.entity("Could not add new item to cart.")
	    	.build();
	}

	@GET
	public Response get(
		@QueryParam("username") String username,
		@QueryParam("productId") int itemId)
	{
		if (username != null && itemId <= 0)
			return get_cart(username);

		if (username == null && itemId > 0)
			return get_buyers(itemId);

		return Response
			.status(500)
			.entity("Invalid parameters.")
			.build();
	}

	@DELETE
	public Response delete_item_from_cart(
		@QueryParam("cartId") int cartId,
		@QueryParam("productId") int itemId)
	{
		boolean worked = cartService.delete_item_from_cart(cartId, itemId);

		if (worked)
			return Response
				.status(200)
		    	.entity("Item removed from cart.")
		    	.build();

		return Response
			.status(500)
	    	.entity("Could remove item from cart.")
	    	.build();
	}

	@PUT
	@Path("/purchase/{cartId}")
	public Response buy_cart(
		@PathParam("cartId") int cartId)
	{
		boolean cart_exists = cartService.cart_exists(cartId);

		if (!cart_exists)
			return Response
				.status(500)
		    	.entity("No cart found with the specified ID.")
		    	.build();

		cartService.buy_cart(cartId);

		return Response
			.status(200)
	    	.entity("Order processed.")
	    	.build();
	}

	public Response get_cart(String username)
	{
		int cart_id  = cartService.get_cart_id(username);

		if (cart_id == 0)
			return Response
				.status(500)
				.entity("No cart for username found.")
				.build();

		Collection<Product> products       = cartService.get_cart_items(cart_id);
		JSONArray           products_array = new JSONArray();
		JSONObject          json;

		for (Product product : products)
		{
			json = new JSONObject(product.to_JSON());
			products_array.put(json);
		}

		json = new JSONObject(); 
		json.put("cartId", cart_id)
			.put("items", products_array);

		return Response
			.status(200)
			.entity(json.toString())
			.build();	 
	}

	public Response get_buyers(int itemId)
	{
		Collection<Customer> customers = cartService.get_customers(itemId);
		if (customers == null)
			return Response
				.status(500)
		    	.entity("No items found in orders.")
		    	.build();

		JSONArray customers_array = new JSONArray();
		JSONObject json;

		for (Customer customer : customers)
		{
			json = new JSONObject(customer.to_JSON());
			customers_array.put(json);
		}

		return Response
			.status(200)
			.entity(customers_array.toString())
			.build();	 
	}
}