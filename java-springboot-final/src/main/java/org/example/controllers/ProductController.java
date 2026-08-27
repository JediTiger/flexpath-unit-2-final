package org.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.exceptions.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.example.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Will this controller need to check access level?
/*
GET /products - Retrieves all products.
GET /products/{id} - Retrieves a product by the id in the path, return a 404 NOT FOUND status code if the product is not found.
POST /products - Creates a new product from the request body and returns the created product with a 201 CREATED http status code.
PUT /products/{id} - Updates an existing product from the request body and returns the updated product,
   return a 404 NOT FOUND status code if the product is not found.
DELETE /products/{id} - Deletes a product by the id in the path and returns the number of rows affected,
   return a 404 NOT FOUND status code if the product is not found.
*/

// Fields are:

@RestController
// TODO: Add mapping so it knows where to direct the request
// TODO: Add in Autowired to DAO
public class ProductController {

}
