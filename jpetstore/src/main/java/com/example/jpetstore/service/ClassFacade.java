package com.example.jpetstore.service;

import java.util.List;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

public interface ClassFacade {
	public List<Class> viewClassList(PagingVO vo);
	public List<Category> getCategoryList();
	public void writeClass(Class newClass);
	public Class findClass(int class_id);
	public void updateClass(Class oriClass);
	public void plusHit(int class_id);
	public int countClass();
	public List<Class> filteringClass(Filtering filtering);
	public void deleteClass(int class_id);
}
