package com.example.jpetstore.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.TeacherAccountDao;
import com.example.jpetstore.dao.UserAccountDao;
import com.example.jpetstore.dao.mybatis.mapper.AccountMapper;
import com.example.jpetstore.dao.mybatis.mapper.TeacherAccountMapper;
import com.example.jpetstore.dao.mybatis.mapper.UserAccountMapper;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

/**
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 */
@Repository
public class MybatisUserAccountDao implements UserAccountDao {

	@Autowired
	private UserAccountMapper userAccountMapper;
	
	public UserAccount getUserAccount(String username) throws DataAccessException {
		return userAccountMapper.getUserAccountByUsername(username);
	}

	public UserAccount getUserAccount(String username, String password) 
			throws DataAccessException {
		return userAccountMapper.getUserAccountByUsernameAndPassword(username, password);
	}
	
	public void insertUserAccount(UserAccount account) throws DataAccessException {
		userAccountMapper.insertUserAccount(account);
	}

	public void updateUserAccount(UserAccount account) throws DataAccessException {
		userAccountMapper.updateUserAccount(account);
	}
	
	public List<UserAccount> getAllUserAccount() throws DataAccessException {
		
		return userAccountMapper.getAllUserAccount();
	}
}