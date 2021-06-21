package com.example.jpetstore.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Payment;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

public interface PaymentDao {
	

  List<Payment> getAllPayments() throws DataAccessException;

}
