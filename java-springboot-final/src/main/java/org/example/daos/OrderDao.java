package org.example.daos;

import org.example.exceptions.DaoException;
import org.example.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

      @Autowired
      public OrderDao(DataSource dataSource) {
         this.jdbcTemplate = new JdbcTemplate(dataSource);
      }
   /*
      For everything but create, will need to access product by id
      create will get a new id for the new order
    */

      // getAll - Retrieves all orders from the table
      public List<Order> getAll() {
         String sql = "SELECT * FROM orders ORDER BY id;";
         return jdbcTemplate.query(sql, this::connectDBToOrder);
      }

      // getById - Retrieves an order by its id
      public Order getById(int id) {
         try {
            String sql = "SELECT * FROM orders WHERE id = ?;";
            return jdbcTemplate.queryForObject(sql, this::connectDBToOrder, id);
         } catch (EmptyResultDataAccessException e) {
            return null;
         }
      }

      // create order - Creates a new order in the table
      public Order create(Order order) {
         String sql = "INSERT INTO orders (username) VALUES (?);";
         try {
            org.springframework.jdbc.support.GeneratedKeyHolder keyHolder = new org.springframework.jdbc.support.GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
               java.sql.PreparedStatement ps = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
               ps.setString(1, order.getUsername());
               return ps;
            }, keyHolder);

            Number newId = keyHolder.getKey();
            if (newId != null) {
               order.setId(newId.intValue());
            }
            return getById(order.getId());
         } catch (Exception e) {
            throw new DaoException("Failed to create order.");
         }
      }

      // update order - Updates an existing order in the table
      public Order update(Order order) {
         String sql = "UPDATE orders SET username = ? WHERE id = ?;";
         int rowsAffected = jdbcTemplate.update(sql, order.getUsername(), order.getId());
         if (rowsAffected == 0) {
            throw new DaoException("Zero rows affected, expected at least one.");
         } else {
            return getById(order.getId());
         }
      }
      // delete order - Deletes an order from the table
      public int delete(int id) {
         String sql = "DELETE FROM orders WHERE id = ?;";
         return jdbcTemplate.update(sql, id);
      }
      private Order connectDBToOrder(ResultSet resultSet, int rowNumber) throws SQLException {
         Order order = new Order();
         order.setId(resultSet.getInt("id"));
         order.setUsername(resultSet.getString("username"));
         return order;
      }
}
