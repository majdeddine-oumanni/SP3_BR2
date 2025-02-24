<%@ page import="Models.Etudiant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

formulaire modification

<% Etudiant etudiant= (Etudiant) request.getAttribute("etudiant"); %>
<form action="etudiants" method="POST">

  <input type="hidden" name="page" value="modifier" >

  <input type="text" name="id"  value="<%= etudiant.getId() %>"> <br>

  <input type="text" name="name"  value="<%= etudiant.getNom() %>"> <br>

  <input type="text" name="prenom"  value="<%= etudiant.getPrenom() %>"> <br>

  <input type="text" name="email"  value="<%= etudiant.getEmail() %>"> <br>

  <input type="text" name="datenaissance"  value="<%= etudiant.getDatenaissance()%>"> <br>


  <button type="submit" >modifier</button>
  <br>

</form>


</body>
</html>