<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form name = "stemform" action="HelloResource" target="_balnk">
<label>Enter one sentence:</label>
<input type="text" name="stemvalue" id ="stemvalue" />
<input type="submit" value="submit">
</form>
</body>
</html>

<%if(request.getParameter("stemvalue") !=null){ %>

<p><label>KbID:</label><%=request.getAttribute("answer") %></p>
<p><label>score:</label><%=request.getAttribute("answer1") %></p>
<%} %>