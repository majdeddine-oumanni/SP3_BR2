<%@ page import="Models.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Course" %>
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
  Etudiant etudiant= (Etudiant) request.getAttribute("etudiant");
  List<Models.Course> courses= (List<Models.Course>) request.getAttribute("courses");
%>

Nom du etudiant : <%= etudiant.getNom() %>  <br>
Prenom : <%=  etudiant.getPrenom() %>  <br>
Email : <%=  etudiant.getEmail() %>  <br>
Date de naissance : <%=  etudiant.getDatenaissance() %>  <br>

les cours qui sont inscrit par cette etudiant :

<table>
  <% if(!courses.isEmpty()){ %>
  <tr>
    <td>title</td>
    <td>description</td>
    <td>unenroll</td>
  </tr>
  <% for(Course course: courses) { %>
  <tr>
    <td> <%= course.getTitle()%> </td>
    <td> <%= course.getDescription()%> </td>
    <td>
      <form action="enrollement" method="post">
        <input type="hidden" name="action" value="unenroll">
        <input  type="hidden" name="etudiantId" value="<%= etudiant.getId()%> ">
        <input   type="hidden" name="courseId" value="<%= course.getId()%> ">
        <button type="submit">supprimer ce cours</button>
      </form>
    </td>
  </tr>
  <% }%>

  <% } else { %>
  <p>aucun cours ... </p>
  <% }  %>
</table>

</body>
</html>