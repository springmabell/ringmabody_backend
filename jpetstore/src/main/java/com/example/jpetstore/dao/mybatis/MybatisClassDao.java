package com.example.jpetstore.dao.mybatis;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.ClassDao;
import com.example.jpetstore.dao.mybatis.mapper.ClassMapper;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

@Repository
public class MybatisClassDao implements ClassDao{
	
	@Autowired
	private ClassMapper classMapper;
	
	public List<Class> viewClassList(PagingVO vo) throws DataAccessException{
		return classMapper.viewClassList(vo);
	}

	@Override
	public List<Category> getCategoryList() throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.getCategoryList();
	}

	@Override
	public void closeEvent(Date today) {
		// TODO Auto-generated method stub
		classMapper.closeEvent(today);
	}

	@Override
	public void writeClass(Class newClass) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.writeClass(newClass);
	}

	@Override
	public Class findClass(int class_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.findClass(class_id);
	}

	@Override
	public void updateClass(Class oriClass) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.updateClass(oriClass);
	}

	@Override
	public void plusHit(int class_id) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.plusHit(class_id);
	}

	@Override
	public int countClass() throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.countClass();
	}

	@Override
	public List<Class> filteringClass(Filtering filtering) throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.filteringClass(filtering);
	}

	@Override
	public void deleteClass(int class_id) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.deleteClass(class_id);
	}

}
