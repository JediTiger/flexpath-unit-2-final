package org.example.controllers;

import org.example.daos.OrderDao;
import org.example.daos.OrderItemDao;
import org.example.models.Order;
import org.example.models.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/* Mappings as follows:
DONE: GET /orders - Retrieves all orders.
DONE: GET /orders/{id} - Retrieves an order by the id in the path, return a 404 NOT FOUND status code if the order is not found.
DONE: POST /orders - Creates a new order from the request body and returns the created order with a 201 CREATED http status code.
DONE: PUT /orders/{id} - Updates an existing order from the request body and returns the updated order,
   return a 404 NOT FOUND status code if the order is not found.
DONE: DELETE /orders/{id} - Deletes an order by the id in the path and returns the number of rows affected,
   return a 404 NOT FOUND status code if the order is not found.
*/

// Fields are: id and username

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
