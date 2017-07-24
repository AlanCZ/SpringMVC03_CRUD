package com.springmvc.bean;

public class Department {
	private Integer id;
	private String dName;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(Integer id, String dName) {
		super();
		this.id = id;
		this.dName = dName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", dName=" + dName + "]";
	}

}
