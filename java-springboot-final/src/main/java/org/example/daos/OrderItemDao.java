package org.example.daos;

import org.example.exceptions.DaoException;
import org.example.models.OrderItem;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


// Fields are id, order_id, product_id and quantity

@Component
public class OrderItemDao {

   // Required declaration and constructor for JDBC
   private final JdbcTemplate jdbcTemplate;

   public OrderItemDao(DataSource dataSource) {
      this.jdbcTemplate = new JdbcTemplate(dataSource);
   }
   /*
      For everything but create, will need to access product by id
      create will get a new id for the new order
    */

   // getAll - Retrieves all orders items from the table
   public List<OrderItem> getAll() {
      String sql = "SELECT * FROM order_items ORDER BY id;";
      return jdbcTemplate.query(sql, this::connectDBToOrderItem);
   }

   // getById - Retrieves an order items by its id
   public OrderItem getById(long id) {
      try {
         String sql = "SELECT * FROM order_items WHERE id = ?;";
         return jdbcTemplate.queryForObject(sql, this::connectDBToOrderItem, id);
      } catch (EmptyResultDataAccessException e) {
         return null;
      }
   }

   // create order - Creates a new order items in the table
   public OrderItem create(OrderItem orderItem) {
      String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?);";
      try {
         jdbcTemplate.update(sql, orderItem.getOrderId(), orderItem.getProductId(), orderItem.getQuantity());
         String findSql = "SELECT * FROM order_items WHERE order_id = ? ORDER BY id DESC LIMIT 1;";
         return jdbcTemplate.queryForObject(findSql, this::connectDBToOrderItem, orderItem.getOrderId());
      } catch (Exception e) {
         throw new DaoException("Failed to create order item.");
      }
   }

   // update order - Updates an existing order item in the table
   public OrderItem update(OrderItem orderItem) {
      String sql = "UPDATE order_items SET order_id = ?, product_id = ?, quantity = ? WHERE id = ?;";
      int rowsAffected = jdbcTemplate.update(sql,
              orderItem.getOrderId(),
              orderItem.getProductId(),
              orderItem.getQuantity(),
              orderItem.getId()
      );
      if (rowsAffected == 0) {
         throw new DaoException("Zero rows affected, expected at least one.");
      } else {
         return getById(orderItem.getId());
      }
   }

   // delete order - Deletes an order item from the table
   public int delete(int id) {
      String sql = "DELETE FROM order_items WHERE id = ?;";
      return jdbcTemplate.update(sql, id);
   }

   // Results map from DB to order item
   private OrderItem connectDBToOrderItem(ResultSet resultSet, int rowNumber) throws SQLException {
      OrderItem item = new OrderItem();
      item.setId(resultSet.getInt("id"));
      item.setOrderId(resultSet.getInt("order_id"));
      item.setProductId(resultSet.getInt("product_id"));
      item.setQuantity(resultSet.getInt("quantity"));
      return item;
   }
}
