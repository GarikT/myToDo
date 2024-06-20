<%@ page import="org.example.mytodo.model.ToDo" %><%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 6/19/24
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update ToDo</title>
</head>
<body>
<%
    ToDo todo = (ToDo) request.getAttribute("toDo");
%>
Update ToDo<br>
<form method="post" action="/updateToDo">
    <input type="hidden" name="toDoId" value="<%=todo.getId()%>">
    Title: <input type="text" name="title" value="<%=todo.getTitle()%>"><br>
    <label for="done-todo">Done with todo?</label>
    <select id="done-todo" name="done-todo">
        <option value="yes">Yes</option>
        <option value="no">No</option>
    </select>
    <br><input type="submit" value="update">
</form>
</body>
</html>
