<%@ page import="org.omg.CORBA.Request" %>
<%--
  Created by IntelliJ IDEA.
  User: Marianne
  Date: 16/09/2014
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel=stylesheet type="text/css" href="/css/style.css"/>
    <title>

    </title>
</head>
<body>

<div class="menu">

    <fieldset>
        <form method="post" action="HomeServlet">

            <p>Menu</p>

            <input name="bouton_users" value="Afficher les utilisateurs" type="submit" class="bouton">
        </form>

        <form method="post" action="listtweets&userid">
            <select name="utilisateurs" class="liste">
                <option value="choix">Premier choix</option>
                <option value="choix">Deuxième choix</option>
                <option value="choix">Troisième choix</option>
            </select>
            <input name="bouton_tweets" value="Récupérer les tweets d'un utilisateur" type="submit" class="bouton">
        </form>


        <form method="post" action="listalltweets">
            <input name="bouton_alltweets" value="Récupérer tous les tweets" type="submit" class="bouton">
        </form>

        <form method="post" action="updatedata">
            <input name="bouton_update" value="Mettre à jour la base de données" type="submit" class="bouton">
        </form>
    </fieldset>
</div>

<div class="affichage">

    <%= request.getAttribute("user") %>

</div>

</body>
</html>
