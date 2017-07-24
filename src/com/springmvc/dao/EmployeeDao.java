package com.springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.bean.Employee;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@Repository
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmps() {
		String sql = "select * from employee";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		return jdbcTemplate.query(sql, rowMapper);
	}

	public void save(Employee employee) {
		if (employee.getId() != null) {
			updateById(employee);
		}
	}

	public void addEmp(Employee employee) {
		String sql = "insert into employee(lastname, email, gender, did) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, employee.getLastName(), employee.getEmail(), employee.getGender(), employee.getdId());
	}

	public void deleteEmpById(Integer id) {
		String sql = "delete from employee where id = ?";
		jdbcTemplate.update(sql, id);
	}

	public Employee getEmpById(Integer id) {
		String sql = "select * from employee where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	public void updateById(Employee emp) {
		String sql = "update employee set email = ?, gender = ?, did = ? where id = ?";
		jdbcTemplate.update(sql, emp.getEmail(), emp.getGender(), emp.getdId(), emp.getId());
	}

}
