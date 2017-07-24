package com.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.bean.Employee;
import com.springmvc.dao.DepartmentDao;
import com.springmvc.dao.EmployeeDao;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeDao eDao;
	@Autowired
	private DepartmentDao dDao;
	
	@ModelAttribute
	public void setEncoding(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 查询所有员工
	 */
	@RequestMapping("/getAllEmps")
	public String getAllEmps(Map<String, Object> map){
		System.out.println("查看员工信息.....");
		List<Employee> emps = eDao.getAllEmps();
		System.out.println(emps);
		map.put("emps", emps);
		return "employeeInfo";
	}
	
	/*
	 * 跳转至新增员工页面
	 */
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String getToAddEmp(Map<String, Object> map, Employee employee){
		System.out.println("跳转到员工页面......");
		map.put("employee", new Employee());
		map.put("departments", dDao.getDepartments());
		return "saveEmp";
	}
	
	/*
	 * 新增员工
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public String addEmp(Employee employee){
		System.out.println("新增员工......");
		//第一步，添加至数据库
		try {
			eDao.addEmp(employee);
			System.out.println("添加成功.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/getAllEmps";
	}
	
	/*
	 * 删除员工信息
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String deleteEmp(@PathVariable("id")Integer id){
		System.out.println("删除员工操作.....,员工ID： " + id);
		try {
			eDao.deleteEmpById(id);
			System.out.println("删除成功.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/getAllEmps";
	}

	/*
	 * 修改员工信息
	 * 	1. 获取emp信息放入map中,被@ModelAttribute标注的方法首先被执行
	 */
	@ModelAttribute
	public void getEmpById(@RequestParam(value="id", required=false)Integer id,
				Map<String, Object> map){
		if (id != null) {
			Employee empById = eDao.getEmpById(id);
			map.put("emp", empById);
		}
	}
	
	/*
	 * 修改员工信息
	 * 	2. 查询emp信息并转到saveEmp页面
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String goToUpdateEmp(@PathVariable("id")Integer id, Map<String, Object> map){
		System.out.println("更改员工信息.......");
		map.put("employee", eDao.getEmpById(id));
		map.put("departments", dDao.getDepartments());
		return "saveEmp";
	}
	
	
	/*
	 * 修改员工信息
	 * 	3. 修改emp信息并转到员工列表页面
	 */
	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String updateEmp(@ModelAttribute("emp")Employee employee){
		eDao.save(employee);
		return "redirect:/getAllEmps";
	}
	
}
