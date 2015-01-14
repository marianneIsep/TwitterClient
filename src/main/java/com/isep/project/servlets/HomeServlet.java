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
import java.util.ArrayList;
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
        request.setAttribute("listUser",wsConfiguration.getUsers());
        request.setAttribute("texte", "Choisissez une option dans le menu");
        dispatcher.forward(request, response) ;


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String resultView = JSPLOCATION + "result.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(resultView);

        WSConfiguration wsConfiguration = new WSConfiguration();
        if(request.getParameter("bouton_users")!=null){
            String myText = listUsers(wsConfiguration);
            request.setAttribute("texte", myText);

        }
        else if (request.getParameter("bouton_tweets")!=null){
            long userId = Long.parseLong(request.getParameter("ddl_user"));
            String myText = listTweets(wsConfiguration,userId);
            request.setAttribute("texte", myText);

        }
        else if (request.getParameter("bouton_alltweets")!=null){
            String myText = listUsersWithTweets(wsConfiguration);
            request.setAttribute("texte", myText);
        }
        else if (request.getParameter("bouton_update")!=null){
            request.setAttribute("texte", updateData(wsConfiguration));
        }
        request.setAttribute("listUser",wsConfiguration.getUsers());
        dispatcher.forward(request,response);
    }

// Méthode pour récupérer tous les noms des utilisateurs
    private String listUsers(WSConfiguration wsConfiguration){
        List<User> maListe = wsConfiguration.getUsers();
        String output = "Liste de tous les utilisateurs : </br>" ;
        output += "<ul>";
        for(User user : maListe){
            output += "<li>" + user.getName() + "</li>";
        }
        output += "</ul>";
        return output;
    }

//Méthode pour récupérer tous les tweets
    private String listUsersWithTweets(WSConfiguration wsConfiguration){
        List<User> userList = wsConfiguration.getUsers();
        String output = "Liste des utilisateurs avec leurs tweets : ";

        for(User user : userList){
            output += "<ul>";
            output += "<li><b>" + user.getName() + "</b></li>";
            for(Tweet tweet : user.getTweets())
            {
                output += "<ul>";
                output += "<li>"+ tweet.getDate() + " : " + tweet.getMessage() +"</li>";
                output += "</ul>";
            }
            output += "</ul>";

        }

        return output;
    }

 //Méthode pour récupérer les tweets d'un utilisateur
    private String listTweets(WSConfiguration wsConfiguration, long id){
        List<Tweet> tweetList = wsConfiguration.getTweetsFromUser(id);
        String output = "Liste de Tweets de l'utilisateur : </br>";
        output += "<ul>";
        for(Tweet tweet : tweetList){
            output += "<li>";
            output += tweet.getDate() + " : ";
            output += tweet.getMessage();
            output += "</li>";
        }
        output += "</ul>";
        return output;
    }

//Méthode pour mettre à jour la BDD et afficher si cela a été fait
    private String updateData(WSConfiguration wsConfiguration){
        if (wsConfiguration.addTweets())
            return "Les tweets ont été mis à jour";
        else
            return "Problème ! Aucun tweet mis à jour";
    }


}
