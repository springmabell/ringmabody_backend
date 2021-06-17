package com.example.jpetstore.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.TeacherAccount;

public interface TeacherAccountDao {
	
  List<TeacherAccount> getAllTeacherAccount() throws DataAccessException;

  TeacherAccount getTeacherAccount(String teacher_id);

  TeacherAccount getTeacherAccount(String teacher_id, String teacher_pwd);

}
