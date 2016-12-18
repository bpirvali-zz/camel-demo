package com.bp.samples.repository;

import com.bp.samples.model.Customer;
import com.bp.samples.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by behzad.pirvali on 12/12/16.
 public Order(long id, Customer customer, String orderNumber,
 Date timeOrderPlaced, Date lastUpdate, String status) {

 */
@Component
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Order> getOrders() {
        List<Order> orders = null;
        String sql =  "SELECT o.iD, o.ORDERNUMBER, o.TIMEORDERPLACED, " +
                "o.LASTUPDATE, o.STATUS, c.ID C_ID, c.FIRSTNAME, c.LASTNAME, " +
                "c.EMAIL FROM orders.orders o INNER JOIN orders.customer c ON " +
                "o.CUSTOMER_ID =c.iD";

        orders = jdbcTemplate.query(sql,  (rs, rowNum) -> {
            Order o = new Order();
            o.setId(rs.getLong("id"));

            Customer c = new Customer();
            c.setId(rs.getLong("C_ID"));
            c.setFirstName(rs.getString("FIRSTNAME"));
            c.setLastName(rs.getString("LASTNAME"));
            c.setEmail(rs.getString("EMAIL"));

            o.setCustomer( c );
            o.setLastUpdate(rs.getDate("LASTUPDATE"));
            o.setOrderNumber(rs.getString("ORDERNUMBER"));
            o.setStatus(rs.getString("STATUS"));
            o.setTimeOrderPlaced(rs.getDate("TIMEORDERPLACED"));

            return o;
        });
        return orders;

    }
}
