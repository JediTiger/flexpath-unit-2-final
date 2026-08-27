package org.example.daos;

import org.example.exceptions.DaoException;
import org.example.models.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.example.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
  From the README.md, my task is to create DAO and controller classes for 3 of the 5 SQL tables provided:
  Product, Order & OrderItem. So this should be 6 files.

  To start off small, I'm starting with Product since that's the basis for everything else.
  You have to have products to order.
*/

// Fields are id and username
// DAO's job is to be the middleman for the database. Controller asks DAO to get data and send it thru

@Component
public class OrderDao {

      // Required declaration and constructor for JDBC
      private final JdbcTemplate jdbcTemplate;

      public OrderDao(DataSource dataSource) {
         this.jdbcTemplate = new JdbcTemplate(dataSource);
      }
   /*
      For everything but create, will need to access product by id
      create will get a new id for the new order
    */

      // TODO: getAll - Retrieves all orders from the table
      public List<Order> getAll() {
         String sql = "SELECT * FROM orders ORDER BY id;";
         return jdbcTemplate.query(sql, this::connectDBToOrder);
      }

      // TODO: getById - Retrieves an order by its id
      public Order getById(int id) {
         try {
            String sql = "SELECT * FROM orders WHERE id = ?;";
            return jdbcTemplate.queryForObject(sql, this::connectDBToOrder, id);
         } catch (EmptyResultDataAccessException e) {
            return null;
         }
      }

      // TODO: create order - Creates a new order in the table

      // TODO: update order - Updates an existing order in the table

      // TODO: delete order - Deletes an order from the table


}
