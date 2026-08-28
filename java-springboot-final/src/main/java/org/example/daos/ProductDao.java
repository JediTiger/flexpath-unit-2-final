package org.example.daos;

import org.example.exceptions.DaoException;
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

// Fields are id, product and price
// DAO's job is to be the middleman for the database. Controller asks DAO to get data and send it on

@Component
public class ProductDao {

   // Required declaration and constructor for JDBC
   private final JdbcTemplate jdbcTemplate;

   public ProductDao(DataSource dataSource) {
      this.jdbcTemplate = new JdbcTemplate(dataSource);
   }
   /*
      For everything but create, will need to access product by id
      create will get a new id for the new product
    */

   // getAll - Retrieves all items from the table
   public List<Product> getAll() {
      String sql = "SELECT * FROM products";
      return jdbcTemplate.query(sql, this::connectDBToProduct);
   }

   // getById - Retrieves an item by its id
   public Product getById(int id) {
      try {
         String sql = "SELECT * FROM products WHERE id = ?;";
         return jdbcTemplate.queryForObject(sql, this::connectDBToProduct, id);
      } catch (EmptyResultDataAccessException e) {
         return null;
      }
   }
      // create product - Creates a new item in the table
      public Product create(Product product) {
         String sql = "INSERT INTO products (name, price) VALUES (?, ?);";
         try {
            jdbcTemplate.update(sql, product.getName(), product.getPrice());

            String findSql = "SELECT * FROM products WHERE name = ? ORDER BY id DESC LIMIT 1;";
            return jdbcTemplate.queryForObject(findSql, this::connectDBToProduct, product.getName());
         } catch (Exception e) {
            throw new DaoException("Failed to create product.");
         }
      }

      // update product - Updates an existing item in the table
         public Product update(Product product) {
            String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?;";
            int rowsAffected = jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());
            if (rowsAffected == 0) {
               throw new DaoException("Zero rows affected, expected at least one.");
            } else {
               return getById(product.getId());
            }
         }

      // delete product - Deletes an item from the table
         public int delete(int id) {
            String sql = "DELETE FROM products WHERE id = ?;";
            return jdbcTemplate.update(sql, id);
         }


      private Product connectDBToProduct(ResultSet resultSet, int rowNumber) throws SQLException {
         Product product = new Product();
         product.setId(resultSet.getInt("id"));
         product.setName(resultSet.getString("name"));
         product.setPrice(resultSet.getBigDecimal("price"));
         return product;
      }
}