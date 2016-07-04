<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>

	<h1>Storing in File System</h1>
	<form method="POST" action="uploadFile" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
	
	 <c:forEach var="file" items="${fileList}">
         <tr>
            <td>${file.data}</td>
         </tr>
     </c:forEach>
	-------------------------------------------
	
	
	<h1>Storing in database</h1>
	<form method="post" action="doUpload" enctype="multipart/form-data">
										
	Pick file
	<input type="file" required name="fileUpload" size="50" />
												
	<br/><br/>
	<input type="submit" value="Upload" />
										
	</form>
									
	Download file: <br/> <br/>
	<form  action="download">
	Enter a ID: <input type="text" name="fileName" > <br/> <br/>
	<input type="submit" value="Download file" >
	</form>
</body>

</html>