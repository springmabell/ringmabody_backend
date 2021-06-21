package com.example.jpetstore.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.PagingVO;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

public interface UserAccountDao {

	void deleteUser(String user_id) throws DataAccessException;

	int countUser() throws DataAccessException;
	
	List<UserAccount> selectUser(PagingVO vo) throws DataAccessException;
	
  UserAccount getUserAccount(String username) throws DataAccessException;

  UserAccount getUserAccount(String username, String password) throws DataAccessException;
  
  void insertUserAccount(UserAccount account) throws DataAccessException;

  void updateUserAccount(UserAccount account) throws DataAccessException;
	
  List<UserAccount> getAllUserAccount() throws DataAccessException;

}
