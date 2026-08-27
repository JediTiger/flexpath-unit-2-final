package org.example.controllers;

import org.example.daos.OrderDao;
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


/* TODO: Mappings as follows:
GET /orders - Retrieves all orders.
GET /orders/{id} - Retrieves an order by the id in the path, return a 404 NOT FOUND status code if the order is not found.
POST /orders - Creates a new order from the request body and returns the created order with a 201 CREATED http status code.
PUT /orders/{id} - Updates an existing order from the request body and returns the updated order,
   return a 404 NOT FOUND status code if the order is not found.
DELETE /orders/{id} - Deletes an order by the id in the path and returns the number of rows affected,
   return a 404 NOT FOUND status code if the order is not found.
*/

// Fields are: id, name and price

@RestController
// TODO: Mapping
// TODO: From UserDao may need to correct
@PreAuthorize("isAuthenticated()")
// This controller will have classes that match those from the ProductDao
public class OrderController {
   @Autowired
   private OrderDao orderDao;

   // get all

   // get by id

   // create

   // update

   // delete
}
