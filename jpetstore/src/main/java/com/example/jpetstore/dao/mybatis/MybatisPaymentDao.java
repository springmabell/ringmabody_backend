package com.example.jpetstore.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.PaymentDao;
import com.example.jpetstore.dao.TeacherAccountDao;
import com.example.jpetstore.dao.UserAccountDao;
import com.example.jpetstore.dao.mybatis.mapper.PaymentMapper;
import com.example.jpetstore.dao.mybatis.mapper.TeacherAccountMapper;
import com.example.jpetstore.dao.mybatis.mapper.UserAccountMapper;
import com.example.jpetstore.domain.Payment;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

/**
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 */
@Repository
public class MybatisPaymentDao implements PaymentDao {

	@Autowired
	private PaymentMapper paymentMapper;
	
	public List<Payment> getAllPayments() throws DataAccessException {
		
		return paymentMapper.getAllPayments();
	}
}