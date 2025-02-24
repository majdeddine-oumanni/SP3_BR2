<%--
  Created by IntelliJ IDEA.
  User: MAJD
  Date: 2/20/2025
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Models.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course List</title>
</head>
<body>
<h2>List of Courses</h2>
<table border="1">
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>

    <div>

        listes des etudiants
        <a href="etudiants">etudiants</a>
    </div>

    liste des cours :
    <% List<Course> courses = (List<Course>) request.getAttribute("courses");
        for (Course course : courses) { %>
    <tr>
        <td><%= course.getTitle() %></td>
        <td><%= course.getDescription() %></td>
        <td>
            <a href="enrollement?action=listetudiants&&coursId=<%= course.getId() %>">afficher les'etudiants iscrit dans ce cours</a></td>
    </tr>
    <% } %>
    </tbody>
</table>
<a href="courses?action=add">Ajouter un course</a>
<a href="etudiants?action=add">Ajouter un'etudiant</a>


<a href="enrollement?action=enroll">enroll</a>

<a href="enrollement?action=unenroll">unenroll</a>



</body>
</html>
