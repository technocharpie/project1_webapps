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
@Path("/customers")
public class CustomerController extends HttpServlet
{

	private CustomerService customerService = new CustomerService();

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
	@Path("/{username}")
	public Response select_customer(@PathParam("username") String username) 
	{
		Customer customer = customerService.select_customer(username);
		JSONObject json = new JSONObject(customer.to_JSON());

		if (customer != null && customer.get_id() != 0)
			return Response
			.status(200)
		    .entity(json.toString()).build();

		return Response
		.status(404)
	    .entity("Customer username: " + username + " not found.").build();
	}

	@POST
	public Response insert_customer(
		@QueryParam("fname") String fname,
		@QueryParam("lname") String lname,
		@QueryParam("username") String username,
		@QueryParam("email") String email) 
	{
		boolean worked = customerService.insert_customer(fname, lname, username, email);

		if (worked)
			return Response
			.status(200)
		    .entity(username).build();

		return Response
		.status(500)
	    .entity("Could not add new customer.").build();
	}

	@PUT
	public Response update_customer(
		@QueryParam("fname") String fname,
		@QueryParam("lname") String lname,
		@QueryParam("username") String username,
		@QueryParam("email") String email) 
	{
		boolean worked = customerService.update_customer(fname, lname, username, email);

		if (worked)
			return Response
			.status(200)
		    .entity(username + "'s information updated.").build();

		return Response
		.status(500)
	    .entity("Could not update customer.").build();
	}

	@DELETE
	@Path("/{username}")
	public Response delete_customer(@PathParam("username") String username) 
	{
		boolean worked = customerService.delete_customer(username);

		if (worked)
			return Response
			.status(200)
		    .entity(username + "'s information deleted.").build();

		return Response
		.status(500)
	    .entity("Could not delete customer.").build();
	}

}