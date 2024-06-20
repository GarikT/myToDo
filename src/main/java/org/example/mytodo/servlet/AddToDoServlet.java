package org.example.mytodo.servlet;

import org.example.mytodo.manager.ToDoManager;
import org.example.mytodo.model.Status;
import org.example.mytodo.model.ToDo;
import org.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addTodo")
public class AddToDoServlet extends HttpServlet {
    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addToDo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        Date createDate = new Date();
        Date finishDate = null;
        Status status = new Status(1, "NEW");

        toDoManager.add(ToDo.builder()
                        .title(title)
                        .createDate(createDate)
                        .finishDate(finishDate)
                        .user(user)
                        .status(status)
                .build());
        resp.sendRedirect("/home");
    }
}
