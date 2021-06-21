package com.example.jpetstore.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

public interface UserAccountDao {
	

  UserAccount getUserAccount(String username) throws DataAccessException;

  UserAccount getUserAccount(String username, String password) throws DataAccessException;
  
  void insertUserAccount(UserAccount account) throws DataAccessException;

  void updateUserAccount(UserAccount account) throws DataAccessException;
	
  List<UserAccount> getAllUserAccount() throws DataAccessException;

}
