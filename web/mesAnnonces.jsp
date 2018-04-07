<%@ page import="java.util.List" %>
<%@ page import="fr.projet.ProjetLBC.beans.Annonce" %><%--
  Created by IntelliJ IDEA.
  User: cesardindeleux
  Date: 06/04/2018
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes annonces</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="JS/materialize.js"></script>
    <link rel="stylesheet" href="CSS/materialize.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>
<table>
<%
    List<Annonce> myAnnonces = (List)request.getAttribute("ANNONCES");
    for (Annonce annonce : myAnnonces) {
        out.println("<ul class=\"collection\">");
        out.println("<li class=\"collection-item avatar\">");
        out.println("<i class=\"material-icons circle green\">assignment</i>\n");
        out.println("<span class=\"title\">"+annonce.getTitre()+"</span>");
        out.println("<p>"+ annonce.getPrix() + " € <br>");
        out.println(" Date de création : \n"+annonce.getCreation().toString()+ "</p>");
        out.println(" <a href=\"#!\" class=\"secondary-content\"><i class=\"material-icons\">Modifier</i></a>\n" +
                "    </li>\n" +
                "  </ul>");
   }
%>

</table>

</body>
</html>
