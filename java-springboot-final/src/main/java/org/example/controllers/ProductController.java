package org.example.controllers;

import org.example.daos.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.exceptions.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.example.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


/* TODO: Mappings as follows:
DONE: GET /products - Retrieves all products.
DONE: GET /products/{id} - Retrieves a product by the id in the path, return a 404 NOT FOUND status code if the product is not found.
POST /products - Creates a new product from the request body and returns the created product with a 201 CREATED http status code.
PUT /products/{id} - Updates an existing product from the request body and returns the updated product,
   return a 404 NOT FOUND status code if the product is not found.
DELETE /products/{id} - Deletes a product by the id in the path and returns the number of rows affected,
   return a 404 NOT FOUND status code if the product is not found.
*/

// Fields are: id, name and price

// TODO: Will this controller need to check access level?
// TODO: Add mapping so it knows where to direct the request
/* TODO: Something in the readme about admin access; reread that
   It says that admins are able to create, edit or delete items so yes
   Copy ref from ProfileController for now
*/
@RestController
@RequestMapping("/products")
// TODO: From UserDao may need to correct
@PreAuthorize("isAuthenticated()")
// This controller will have classes that match those from the DAO
public class ProductController {
   @Autowired
   private ProductDao productDao;

   // create

   // get all
   @GetMapping
   public List<Product> getAll() {
      return productDao.getAll();
   }

   // get by id
   @GetMapping("/{id}")
   public Product get(@PathVariable int id) {
      Product product = productDao.getById(id);
      if (product == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
      }
      return product;
   }

   // update

   // delete
}
