/*--------------------
    Web Apps UF Fall 2018, Dr. Brown
    copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

package com.store.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.dao.*;
import com.store.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner 
{

    private static final Logger log = LoggerFactory.getLogger(Application.class);



    public static void main(String args[]) 
    {
        SpringApplication.run(Application.class, args);
    }



    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    public void run(String... strings) throws Exception 
    {
        ProductDAO productDAO = new ProductDAO(jdbcTemplate);

        log.info("\n\n\n");
        log.info("Selecting product (id = 10): ");
        log.info(productDAO.select_product(10).toString());
        log.info("\n\n\n");
        log.info("Inserting product (name = 'Yeezys', msrp = 69.69, salePrice = 42.0, upc = 138258222, description = 'get boosted', brandName = 'yeet', size = '12', color = 'black', gender = 'male'): ");
        productDAO.insert_product(new Product("Yeezys", 69.69, 42.0, 138258222, "get boosted", "yeet", "12", "black", "male"));
        log.info("\n\n\n");
        log.info("Selecting product (id = 21): ");
        log.info(productDAO.select_product(21).toString());
    }
}
