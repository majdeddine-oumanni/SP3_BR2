<%@ page import="Models.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Etudiant" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Enrollment</title>
</head>

<style>
    tr,td,table{
        border:1px solid black;
    }

</style>
<body>

<%
    Course course= (Course) request.getAttribute("course");
    List<Etudiant> etudiants=(List<Etudiant>) request.getAttribute("etudiants");
%>
Nom du cours : <%= course.getTitle() %>  <br>
description du cours : <%=  course.getDescription() %>  <br>

Liste des etudiants inscrit dans ce cours :

<table>
    <% if(etudiants.size() > 0){ %>
    <tr>
        <td>nom</td>
        <td>prenom</td>
        <td>Date de naissance</td>
        <td>Email</td>
        <td>unenroll</td>
    </tr>

    <% for(Etudiant etudiant: etudiants) { %>
    <tr>
        <td> <%= etudiant.getNom()%> </td>
        <td> <%= etudiant.getPrenom()%> </td>
        <td> <%= etudiant.getDatenaissance()%> </td>
        <td> <%= etudiant.getEmail()%> </td>
        <td>
            <form action="enrollement" method="post">
                <input type="hidden" name="action" value="unenroll">
                <input  type="hidden" name="etudiantId" value="<%= etudiant.getId()%> ">
                <input   type="hidden" name="courseId" value="<%= course.getId()%> ">
                <button type="submit">supprimer cette etudiant de ce cours</button>
            </form>

        </td>

    </tr>
    <% }%>

    <% } else { %>
    <p>personne n est inscrit </p>
    <% }  %>
</table>


</body>
</html>