package org.example.controllers;

import org.example.daos.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.example.models.Product;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/* Mappings as follows:
DONE: POST /products - Creates a new product from the request body and returns the created product with a 201 CREATED http status code.
DONE: GET /products - Retrieves all products.
DONE: GET /products/{id} - Retrieves a product by the id in the path, return a 404 NOT FOUND status code if the product is not found.
DONE: PUT /products/{id} - Updates an existing product from the request body and returns the updated product,
   return a 404 NOT FOUND status code if the product is not found.
DONE: DELETE /products/{id} - Deletes a product by the id in the path and returns the number of rows affected,
   return a 404 NOT FOUND status code if the product is not found.
*/

// Fields are: id, name and price

@RestController
@CrossOrigin
@RequestMapping({ "/products", "/api/products"} )
@PreAuthorize("isAuthenticated()")
// This controller will have classes that match those from the ProductDao
public class ProductController {
   @Autowired
   private ProductDao productDao;

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

   // create
   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   public Product create(@RequestBody Product product) {
      return productDao.create(product);
   }

   // update
   @PutMapping("/{id}")
   public Product update(@RequestBody Product product, @PathVariable int id) {
      Product existingProduct = productDao.getById(id);
      if (existingProduct == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
      }
      product.setId(id);
      return productDao.update(product);
   }

   // delete
   @DeleteMapping("/{id}")
   public int delete(@PathVariable int id) {
      int affectedRows = productDao.delete(id);
      if (affectedRows == 0) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
      }
      return affectedRows;
   }
}
