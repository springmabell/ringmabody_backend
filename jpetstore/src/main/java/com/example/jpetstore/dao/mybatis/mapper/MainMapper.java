package com.example.jpetstore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.jpetstore.domain.Class;
@Mapper
public interface MainMapper {
	public List<Class> endingSoon();
	public List<Class> bestClass();
}
