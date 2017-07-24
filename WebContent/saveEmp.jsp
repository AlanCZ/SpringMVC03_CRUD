<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	tr{
		text-align: center;
		height: 40px;
	}
</style>
</head>
<body>
<!-- SpringForm标签 -->
	<h1 align="center">新增员工页面</h1>
	
	<form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
		<table align="center" width="40%" border="1" cellspacing="0">
			<c:if test="${empty employee.id }">
				<tr>
					<td>LASTNAME:</td>
					<td>
						<form:input path="lastName" />
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty employee.id }">
				<!-- 如果id值不为空的话，则就发送put请求，表示修改操作 -->
				<form:hidden path="id"/>
				<!-- 对于_method 不能使用form:hidden标签,
			    	因为modelAttribute对应的bean中没有_method属性，因此回显
			    	数据的时候找不到对应的属性就会报错.
			  	-->
				<input type="hidden" name="_method" value="PUT">
			</c:if>	
			<tr>
				<td>EMAIL:</td>
				<td><form:input path="email"/></td>
			</tr>
			<!-- 构造一个map,用于生成性别的单选框 -->
		<%
			Map<String,String> genders = new LinkedHashMap<String,String>();
			genders.put("1", "男");
			genders.put("0", "女");
			request.setAttribute("genders", genders);
		%>
			<tr>
				<td>GENDER:</td>
				<td>
					<form:radiobuttons path="gender" items="${requestScope.genders }"/>
				</td>
			</tr>
			<tr>
				<td>DEPARTMENT:</td>
				<td>
					<form:select path="dId" items="${requestScope.departments }"
						itemLabel="dName" itemValue="id"></form:select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="SUBMIT">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>