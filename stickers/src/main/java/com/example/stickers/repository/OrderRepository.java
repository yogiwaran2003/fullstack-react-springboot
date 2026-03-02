package com.example.stickers.repository;

import com.example.stickers.entity.Customer;
import com.example.stickers.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

    List<Order> findByOrderStatus(String orderStatus);


    @Query("select o from Order o where o.customer = :customer order by o.createdBy desc")
    List<Order> findOrderByCustomer(@Param("customer") Customer customer);


    @Query("select o from Order o where o.orderStatus=?1")
    List<Order> findOrdersByStatus(String orderStatus);

    @Query(value = "select * from orders o where o.customer_id = :customerId order by o.created_at desc", nativeQuery = true)
    List<Order> findOrderByCustomerWithNativeQuery(@Param("customerId") Long customerId);


    @Query(value = "select * from orders o where o.order_status=?1", nativeQuery = true)
    List<Order> findOrdersByStatusWithNativeQuery(String orderStatus);
}
