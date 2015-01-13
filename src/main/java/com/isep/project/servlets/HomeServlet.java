package com.isep.project.servlets;

import com.isep.project.model.User;
import com.isep.project.utils.WSConfiguration;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Marianne on 31/12/14.
 */
public class HomeServlet extends HttpServlet {
    static private String JSPLOCATION = "/WEB-INF/jsp/";
    static Logger log = Logger.getLogger(HomeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BasicConfigurator.configure();
        String resultView = JSPLOCATION + "result.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(resultView);

        /*WSConfiguration wsConfiguration = new WSConfiguration();

        String text = null;
        for (User user : wsConfiguration.getUsers())
        {
            text += " id : " + user.getId();
            text += " name " + user.getName();
        }

        request.setAttribute("user", text);*/
        log.debug(request.getAttribute("user"));
        //request.setAttribute("user", "Marianne");
        dispatcher.forward(request, response) ;

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("bouton_users")!=null){

        }


    }


}
