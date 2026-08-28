package org.example.controllers;

import org.example.daos.OrderItemDao;
import org.example.models.OrderItem;
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
@RequestMapping({ "/order-items", "/api/order-items", "/api/orders-items" })
// TODO: From UserDao may need to correct
@PreAuthorize("isAuthenticated()")
// This controller will have classes that match those from the ProductDao
public class OrderItemController {
   @Autowired
   private OrderItemDao orderItemDao;

   // TODO: get all
   @GetMapping
   public List<OrderItem> getAll() {
      return orderItemDao.getAll();
   }

   // TODO: get by id
   @GetMapping("/{id}")
   public OrderItem get(@PathVariable int id) {
      OrderItem item = orderItemDao.getById(id);
      if (item == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found");
      }
      return item;
   }

   // TODO: create
   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   public OrderItem create(@RequestBody OrderItem orderItem) {
      return orderItemDao.create(orderItem);
   }
   // TODO: update
   @PutMapping("/{id}")
   public OrderItem update(@RequestBody OrderItem orderItem, @PathVariable int id) {
      OrderItem existingItem = orderItemDao.getById(id);
      if (existingItem == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found");
      }
      orderItem.setId(id);
      return orderItemDao.update(orderItem);
   }
   // TODO: delete
   @DeleteMapping("/{id}")
   public int delete(@PathVariable int id) {
      int affectedRows = orderItemDao.delete(id);
      if (affectedRows == 0) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found");
      }
      return affectedRows;
   }
}
