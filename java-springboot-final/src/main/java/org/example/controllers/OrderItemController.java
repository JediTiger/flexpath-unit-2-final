package org.example.controllers;

import org.example.daos.OrderItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/* TODO: Mappings as follows:
DONE: GET /order-items - Retrieves all order items.
GET /order-items/{id} - Retrieves an order item by the id in the path, return a 404 NOT FOUND status code if the order item is not found.
POST /order-items - Creates a new order item from the request body and returns the created order item with a CREATED http 201 status code.
PUT /order-items/{id} - Updates an existing order item from the request body and returns the updated order item,
   return a 404 NOT FOUND status code if the order item is not found.
DELETE /order-items/{id} - Deletes an order item by the id in the path and returns the number of rows affected,
   return a 404 NOT FOUND status code if the order item is not found.

*/

// Fields are: id, order_id, product_id and quantity

@RestController
@RequestMapping("/order-items")
// TODO: From UserDao may need to correct
@PreAuthorize("isAuthenticated()")
// This controller will have classes that match those from the ProductDao
public class OrderItemController {
   @Autowired
   private OrderItemDao orderItemDao;

   // TODO: get all

   // TODO: get by id

   // TODO: create

   // TODO: update

   // TODO: delete

}
