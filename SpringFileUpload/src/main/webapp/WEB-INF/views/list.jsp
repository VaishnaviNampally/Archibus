<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Success ! Download the files you uploaded to file system from here!
<br/>
 <c:forEach var="file" items="${fileList}">
         <tr>
        <td>  File :  <a href="${file}">${file}</a> <br/><br/></td>
         </tr>
     </c:forEach>
</body>
</html>