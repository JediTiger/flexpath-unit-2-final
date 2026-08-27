package org.example.daos;

import org.springframework.stereotype.Component;

// will need other imports here
/*
  From the README.md, my task is to create DAO and controller classes for 3 of the 5 SQL tables provided:
  Product, Order & OrderItem. So this should be 6 files. To start off small,
  I'm starting with Product since that's the basis for everything else. You have to have products to order.
*/

// Copied imports from UserDao.java for reference and guidance
import org.example.exceptions.DaoException;
import org.example.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


// Classes have getters and setters
// Added requirement Component
@Component
public class ProductDao {

}
