package org.example.mytodo.servlet;

import org.example.mytodo.manager.ToDoManager;
import org.example.mytodo.model.ToDo;
import org.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> todos = toDoManager.getToDoByUserId(user.getId());
        req.setAttribute("todos", todos);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
