package com.epam.training.webapp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleg_Burshinov on 24.02.14.
 */
@WebServlet("/pages/*")
public class Controller extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher  dispatcher = null;
        String path =req.getPathInfo();

        if(path == null){
            throw new ServletException("Missing parameter : go");
        } else if(path.equals("/chat") || path.equals("/chat.htm") || path.equals("/chat.html")){
            dispatcher = req.getRequestDispatcher("/WEB-INF/pages/chat.jsp");
        } else if(path.equals("/login") || path.equals("/login.htm") || path.equals("/login.html")){
            dispatcher = req.getRequestDispatcher("/WEB-INF/pages/login.jsp");
        }

        if(dispatcher!= null){
            dispatcher.forward(req, resp);
        } else {
            throw new ServletException("Controller null dispatcher");
        }
    }
}
