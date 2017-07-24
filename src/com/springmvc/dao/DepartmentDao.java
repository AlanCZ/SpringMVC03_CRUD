package com.springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.bean.Department;

@Repository
public class DepartmentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * 查询部门信息
	 */
	public List<Department> getDepartments(){
		String sql = "select * from department";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
		return jdbcTemplate.query(sql, rowMapper);
	}
	
}
