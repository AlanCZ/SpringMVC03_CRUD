<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<!-- 
		SpringMVC处理静态资源:
			1. web.xml   url-pattern 配置的是 /
			  优秀的REST风格的URL不希望带 .do  .action 等后缀.
			 如果将DispatcherServlet的请求映射配置为/，则SpringMVC将捕获WEB
			 容器的所有请求. 包括静态资源的请求.
			
			2。解决:
				a.让SpringMVC处理带后缀的请求.   不建议.
				b.在springmvc的配置文件中: <mvc:default-servlet-handler/>
	
	-->
<!-- 
	SpringMVC处理静态资源：
		1. web.xml
			处理请求的方式是：/，即对所以的请求都会进行处理
			优秀的REST风格的URL不希望带.do	.action等后缀
			如果将dispatcherServlet的请求映射配置为/，则SpringMVC将捕获WEB容器中的所有请求，包括静态资源的请求
		2. 解决方案
			a.让SpringMVC处理带有后缀的请求（不建议）
			b.在SpringMVC的配置文件中添加标签：<mvc:default-serlvet-handler/>
 -->
<script type="text/javascript">
	$(function () {
		$(".delete").click(function () {
			var href = $(this).attr("href");
			var flag = confirm("确定要删除【" + $(this).attr("id") + "】这个员工吗？");
			if (flag) {
				//对form表单的action属性进行赋值，并提交！
				$("form").attr("action", href).submit();
				//最重要的一步：取消a链接的默认行为，原因：不取消则还是get请求
				return false;
			}
		});
	});
</script>
</head>
<body>
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE">
	</form>

	<h1 align="center">员工信息</h1>
	<c:if test="${empty requestScope.emps }">
		<hr>
		<h2 align="center">暂无员工信息</h2>
	</c:if>
	<c:if test="${not empty requestScope.emps }">
		<table align="center" border="1" width="50%" height="30px" cellspacing="0">
			<thead>
				<tr align="center">
					<td>ID</td>
					<td>LASTNAME</td>
					<td>EMAIL</td>
					<td>GENDER</td>
					<td>DEPARTMENT</td>
					<td colspan="2">OPERATION</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.emps }" var="emp">
				<tr align="center">
					<td>${emp.id }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.gender>0?"男":"女" }</td>
					<td>${emp.dId }</td>
					<!-- 修改操作：先去跳转到 -->
					<td><a href="emp/${emp.id }">EDIT</a></td>
					<!-- 
						针对于RESTful风格删除操作，都要用post请求去处理，但是链接的请求方式是get请求
						所以用js去处理这两个请求
					 -->
					<td><a href="emp/${emp.id }" id="${emp.lastName }" class="delete">DELETE</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<br><br>
	<hr>
	<h2 align="center"><a href="emp">新增员工</a></h2>
</body>
</html>