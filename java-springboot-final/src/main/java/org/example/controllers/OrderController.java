package org.example.controllers;

import org.example.daos.OrderDao;
import org.example.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
@CrossOrigin
@RequestMapping({ "/orders", "/api/orders" })
// TODO: From UserDao may need to correct
@PreAuthorize("isAuthenticated()")
// This controller will have classes that match those from the ProductDao
public class OrderController {
   @Autowired
   private OrderDao orderDao;

   // get all
   @GetMapping
   public List<Order> getAll() {
      return orderDao.getAll();
   }

   // get by id
   @GetMapping("/{id}")
   public Order get(@PathVariable int id) {
      Order order = orderDao.getById(id);
      if (order == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
      }
      return order;
   }

   // create
   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   public Order create(@RequestBody Order order) {
      return orderDao.create(order);
   }

   // update
   @PutMapping("/{id}")
   public Order update(@RequestBody Order order, @PathVariable int id) {
      Order existingOrder = orderDao.getById(id);
      if (existingOrder == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
      }
      order.setId(id);
      return orderDao.update(order);
   }

   // delete
   @DeleteMapping("/{id}")
   public int delete(@PathVariable int id) {
      int affectedRows = orderDao.delete(id);
      if (affectedRows == 0) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
      }
      return affectedRows;
   }
}
