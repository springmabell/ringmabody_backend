package com.example.jpetstore.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.TeacherAccountDao;
import com.example.jpetstore.dao.mybatis.mapper.AccountMapper;
import com.example.jpetstore.dao.mybatis.mapper.TeacherAccountMapper;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.TeacherAccount;

/**
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 */
@Repository
public class MybatisTeacherAccountDao implements TeacherAccountDao {

	@Autowired
	private TeacherAccountMapper teacherAccountMapper;
	
	public List<TeacherAccount> getAllTeacherAccount() throws DataAccessException {
		
		return teacherAccountMapper.getAllTeacherAccount();
	}
}