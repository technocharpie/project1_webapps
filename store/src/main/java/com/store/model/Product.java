/*--------------------
	Web Apps UF Fall 2018, Dr. Brown
	copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

package com.store.model;

public class Product 
{
	private int    id;
	private String name;
	private double msrp;
	private double salePrice;
	private int    upc;
	private String description;
	private String brand; 
	private String size;
	private String color;
	private String gender;


	//========== Constructors ==========//
	public Product(String name, double msrp, double salePrice, int upc,
					String description, String brand, String size, String color, String gender)
	{
		this.name 		 = name;
		this.msrp 		 = msrp;
		this.salePrice 	 = salePrice;
		this.upc 		 = upc;
		this.description = description;
		this.brand 		 = brand;
		this.size 		 = size;
		this.color 		 = color;
		this.gender 	 = gender;
	}

	public Product(Product product)
	{
		this.id 		 = product.id;
		this.name 		 = product.name;
		this.msrp 		 = product.msrp;
		this.salePrice 	 = product.salePrice;
		this.upc 		 = product.upc;
		this.description = product.description;
		this.brand 		 = product.brand;
		this.size 		 = product.size;
		this.color 		 = product.color;
		this.gender 	 = product.gender;
	}


	//========== GETers ==========//
	public int get_id()
	{
		return id;
	}

	public String get_name()
	{
		return name;
	}

	public double get_msrp()
	{
		return msrp;
	}

	public double get_salePrice()
	{
		return salePrice;
	}

	public int get_upc()
	{
		return upc;
	}

	public String get_description()
	{
		return description;
	}

	public String get_brand()
	{
		return brand;
	}

	public String get_size()
	{
		return size;
	}

	public String get_color()
	{
		return color;
	}

	public String get_gender()
	{
		return gender;
	}


	//========== SETers ==========//
	public void set_id(int id)
	{
		this.id = id;
	}

	public void set_name(String name)
	{
		this.name = name;
	}

	public void set_msrp(double msrp)
	{
		this.msrp = msrp;
	}

	public void set_salePrice(double salePrice)
	{
		this.salePrice = salePrice;
	}

	public void set_upc(int upc)
	{
		this.upc = upc;
	}

	public void set_description(String description)
	{
		this.description = description;
	}

	public void set_brand(String brand)
	{
		this.brand = brand;
	}

	public void set_size(String size)
	{
		this.size = size;
	}

	public void set_color(String color)
	{
		this.color = color;
	}

	public void set_gender(String gender)
	{
		this.gender = gender;
	}


	//========== Misc. ==========//
	@Override
    public String toString() 
    {
        return String.format("Product [id = %d, name = '%s', msrp = '%f', salePrice = '%f', upc = '%d', description = '%s', brand = '%s', size = '%s', color = '%s', gender = '%s']", 
        	id, name, msrp, salePrice, upc, description, brand, size, color, gender);
    }

    public String to_JSON()
    {
    	return String.format("{\"id\":%d,\"name\":\"%s\",\"msrp\":%f,\"salePrice\":%f,\"upc\":%d,\"description\":\"%s\",\"brand\":\"%s\",\"size\":\"%s\",\"color\":\"%s\",\"gender\":\"%s\"}", 
        	id, name, msrp, salePrice, upc, description, brand, size, color, gender);
    }
}