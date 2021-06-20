package com.example.jpetstore.service;

import com.example.jpetstore.domain.Order;

public interface OrderFacade {
	public void insertOrder(Order order);
	public void updateParticipant(int class_id);
}
