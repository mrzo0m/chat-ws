package com.epam.training.webapp;

import stateless.bean.CalculatorLocal;

import javax.ejb.EJB;
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
@WebServlet("/calcultaor")
public class StartController extends HttpServlet{

    private CalculatorLocal calculator;

    /**
     * Injecting the EJB
     */
    @EJB(name = "calculator")
    public void setCalculator(CalculatorLocal calculator)
    {
        this.calculator = calculator;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher  dispatcher = null;
        String path =req.getPathInfo();
        calculator.add(1,1);

        if(path == null){
            throw new ServletException("Missing parameter : go");
        } else if(path.equals("/ejb") || path.equals("/ejb.htm") || path.equals("/login.html")){

            dispatcher = req.getRequestDispatcher("/WEB-INF/pages/login.jsp");
        }

        if(dispatcher!= null){
            dispatcher.forward(req, resp);
        } else {
            throw new ServletException("Controller null dispatcher");
        }
    }
}
