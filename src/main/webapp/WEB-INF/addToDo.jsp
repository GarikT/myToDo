<%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 6/19/24
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add ToDo</title>
</head>
<body>
Add ToDo<br>
<form method="post" action="/addTodo">
    Title: <input type="text" name="title"><br>
    <input type="submit" value="add">
</form>
</body>
</html>
