package com.example.jpetstore.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.PagingVO;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

public interface TeacherAccountDao {
	
	int countTeacher() throws DataAccessException;
	
	List<TeacherAccount> selectTeacher(PagingVO vo) throws DataAccessException;
	
  List<TeacherAccount> getAllTeacherAccount() throws DataAccessException;
  
  List<TeacherAccount> getAllClass() throws DataAccessException;

  TeacherAccount getTeacherAccount(String teacher_id);

  TeacherAccount getTeacherAccount(String teacher_id, String teacher_pwd);

  void insertTeacherAccount(TeacherAccount account);

  void updateTeacherAccount(TeacherAccount account);

}
