<%@ page import="org.example.mytodo.model.ToDo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 6/18/24
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo's</title>
</head>
<body>
<%
    List<ToDo> todos = (List<ToDo>) request.getAttribute("todos");
%>

My ToDo-s | <a href="/addTodo">Add ToDo</a>
<div>
    <table>
        <thead>
        <tr>
            <th>Task</th>
            <th>Create Date</th>
            <th>Finish Date</th>
            <th>Status</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        </thead>
        <%
            if(!todos.isEmpty())
                for(ToDo t : todos){%>
                <tbody>
                <tr>
                    <td><%=t.getTitle()%></td>
                    <td><%=t.getCreateDate()%></td>
                    <td><%=t.getFinishDate()%></td>
                    <td><%=t.getStatus().getStatus()%></td>
                    <td><a href="/deleteToDo?id=<%=t.getId()%>">delete</a></td>
                    <td><a href="/updateToDo?id=<%=t.getId()%>">update</a></td>
                </tr>
                </tbody>
                <%}
        %>
    </table>
</div>
<a href="/logout">Logout</a>
</body>
</html>
