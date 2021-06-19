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

	@Override
	public TeacherAccount getTeacherAccount(String teacher_id) {
		// TODO Auto-generated method stub
		return teacherAccountMapper.getTeacherAccountByTeacher_id(teacher_id);
	}

	@Override
	public TeacherAccount getTeacherAccount(String teacher_id, String teacher_pwd) {
		// TODO Auto-generated method stub
		return teacherAccountMapper.getTeacherAccountByTeacher_idAndTeacher_pwd(teacher_id, teacher_pwd);
	}

	@Override
	public void insertTeacherAccount(TeacherAccount account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTeacherAccount(TeacherAccount account) {
		// TODO Auto-generated method stub
		
	}
}