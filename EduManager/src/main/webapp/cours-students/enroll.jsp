<%@ page import="Models.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

Formulaire enrollement d un cours à un etudiant
<% List<Etudiant> etudiants= (List<Etudiant>) request.getAttribute("etudiants"); %>
<% List<Course> courses= (List<Course>) request.getAttribute("courses"); %>
<form action="enrollement" method="post">

  <input type="text" name="action" value="enroll">


  <select name="etudiantId" id="etudiantId">
    <% for (Etudiant etudiant:etudiants) { %>
    <option value="<%= etudiant.getId() %>"><%= etudiant.getNom()%> <%= etudiant.getNom()%></option>
    <% } %>
  </select>


  <label for="coursId">cours</label>
  <select name="coursId" id="coursId">
    <% for (Course course:courses) { %>
    <option value="<%= course.getId() %>"><%= course.getTitle()%></option>
    <% } %>
  </select>

  <button type="submit">Enroll</button>

</form>

</body>
</html>