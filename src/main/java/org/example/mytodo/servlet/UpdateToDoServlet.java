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

@WebServlet(urlPatterns = "/updateToDo")
public class UpdateToDoServlet extends HttpServlet {
    private ToDoManager toDoManager = new ToDoManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo toDo = toDoManager.getToDoById(id);
        req.setAttribute("toDo", toDo);
        req.getRequestDispatcher("/WEB-INF/updateToDo.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("toDoId"));
        ToDo toDo = toDoManager.getToDoById(id);
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        Date createDate = toDo.getCreateDate();
        String doneToDo = req.getParameter("done-todo");
        System.out.println(doneToDo);
        Date finishDate = null;
        Status status = new Status(1, "NEW");
        if(doneToDo != null) {
            if(doneToDo.equals("yes")) {
                finishDate = new Date();
                status = new Status(2, "DONE");
            }
        }


        toDoManager.update(ToDo.builder()
                        .id(id)
                        .title(title)
                        .createDate(createDate)
                        .finishDate(finishDate)
                        .user(user)
                        .status(status)
                .build());
        resp.sendRedirect("/home");
    }
}
