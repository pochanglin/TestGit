<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.emp.model.*"%>
<%
 EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

<form>

<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>

	<tr>
		<td>ename : </td>
		<td>
			<input type="text" name="ename" value="<%= (empVO == null)? "xxx" : empVO.getEname()%>">
		</td>
	</tr>
	<tr>
		<td>job : </td>
		<td>
			<input type="text" name="job" value="<%= (empVO == null)? "xxx" : empVO.getJob()%>">
		</td>
	</tr>
	<tr>
		<td>hiredate : </td>
		<td>
			<input type="text" name="hiredate" value="<%= (empVO == null)? "2018-01-31" : empVO.getHiredate()%>">
		</td>
	</tr>
	<tr>
		<td>sal : </td>
		<td>
			<input type="text" name="sal" value="<%= (empVO == null)? "10000" : empVO.getSal()%>">
		</td>
	</tr>
	<tr>
		<td>comm : </td>
		<td>
			<input type="text" name="comm" value="<%= (empVO == null)? "100" : empVO.getComm()%>">
		</td>
	</tr>
	<tr>
		<td>deptno : </td>
		<td>
			<input type="text" name="comm" value="<%= (empVO == null)? "10" : empVO.getDeptno()%>">
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="insert">
</form>
</body>
</html>