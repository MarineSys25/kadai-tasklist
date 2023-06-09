package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utilites.TaskListDAO;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager entityManager = TaskListDAO.createEntityManager();

        // 該当のIDのメッセージ1件のみをデータベースから取得
        Task task = entityManager.find(Task.class, Integer.parseInt(request.getParameter("id")));

        entityManager.close();

        // メッセージデータをリクエストスコープにセットしてshow.jspを呼び出す
        request.setAttribute("task", task);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/tasks/show.jsp");
        requestDispatcher.forward(request, response);
    }

}
