package com.example.jpetstore.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpetstore.dao.ClassDao;
import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.domain.Order;

@Service
@Transactional
public class OrderImpl implements OrderFacade{
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public void insertOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.insertOrder(order);
	}

	@Override
	public void updateParticipant(int class_id) {
		// TODO Auto-generated method stub
		orderDao.updateParticipant(class_id);
	}

}
