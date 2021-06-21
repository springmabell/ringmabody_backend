package com.example.jpetstore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Order;

public interface OrderDao {

  List<Order> getOrdersByUsername(String username) throws DataAccessException;

  Order getOrder(int orderId) throws DataAccessException;

	public void insertOrder(Order order) throws DataAccessException;
	public void updateParticipant(int class_id) throws DataAccessException;
}
