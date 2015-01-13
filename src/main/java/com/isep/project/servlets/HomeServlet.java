package com.isep.project.servlets;

import com.isep.project.model.Tweet;
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
import java.util.List;

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

        WSConfiguration wsConfiguration = new WSConfiguration();

        String text = null;
        for (User user : wsConfiguration.getUsers())
        {
            text += " id : " + user.getId();
            text += " name " + user.getName();
        }

        request.setAttribute("user", text);
        log.debug(request.getAttribute("user"));
        request.setAttribute("user", "Marianne");
        dispatcher.forward(request, response) ;

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("bouton_users")!=null){
            //String myText = listUsers();
            String myText="mamamam";
            request.setAttribute("affichage_texte", myText);

        }
        else{

        }


    }


    private String listUsers(){
        WSConfiguration wsConfiguration = new WSConfiguration();
        List<User> maListe = wsConfiguration.getUsers();
        String myText = null;

        for(User user:maListe){
            myText+=user.getName();
        }

        return myText;
    }

    private String listUsersWithTweets(){
        WSConfiguration wsConfiguration = new WSConfiguration();
        List<User> maListe = wsConfiguration.getUsers();
        String myText = null;

        for(User user:maListe){
            myText+=user.getName();
            myText+=user.getTweets();
        }

        return myText;
    }

    private String listTweets(long id){
        WSConfiguration wsConfiguration = new WSConfiguration();
        List<Tweet> maListe = wsConfiguration.getTweetsFromUser(id);
        String myText = null;

        for(Tweet tweet:maListe){
            myText+=tweet.getDate();
            myText+=tweet.getMessage();
        }

        return myText;
    }

    private String updateData(){
        WSConfiguration wsConfiguration = new WSConfiguration();
        String myText = null;
        if(wsConfiguration.addTweets()){
            myText+="Les tweets ont été mis à jour";
            return myText;
        }else{
            myText+="Problème ! Aucun tweet mis à jour";
            return myText;
        }
    }

}
