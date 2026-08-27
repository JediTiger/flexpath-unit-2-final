package org.example.daos;

import org.example.exceptions.DaoException;
import org.springframework.dao.DataAccessException;
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

// Added requirement Component
@Component
public class ProductDao {

   private final JdbcTemplate jdbcTemplate;

   // Constructor for ProductDao.java
   public ProductDao(DataSource dataSource) {
      this.jdbcTemplate = new JdbcTemplate(dataSource);
   }
   /*
      For everything but create, will need to access product by id
      create will get a new id for the new product
    */

   // getAll - Retrieves all items from the table
   public List<Product> getAll() {
      String sql = "SELECT * FROM web_shop.products";
      // Need something to map SQL items to the Product model
      return jdbcTemplate.query(sql, this::connectDBToProduct);
   }
   // getById - Retrieves an item by its id

   // create product - Creates a new item in the table

   // update product - Updates an existing item in the table

   // delete product - Deletes an item from the table

}
