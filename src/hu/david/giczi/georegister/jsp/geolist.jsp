<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List GeoRegistrations</title>
</head>
<body style="background-color:#ffc87a;">

<h1 align="center" style="color:white">Listázás/Keresés</h1><hr><br>
<a href="geostart.jsp" style="float:right"><button>Vissza</button></a>



<form action="preRouter" accept-charset="UTF-8">
   		 
   		 <select name="year">
	
		 <option value="all">Összes</option>	
		<c:forEach items="${years}" var="year">
   		 <option value="${year}">${year}.</option>
		</c:forEach>
		</select>
   		<input type="submit" value="évi munkák mutatása" />
		</form><br>

<form action="search" accept-charset="UTF-8">
<input type="text" name="search"/>
<input type="submit" value="Keresés" />

</form>


<input type="hidden" value="${searcheddata}" id="searched">
<input type="hidden" value="${emptylist}" id="empty">
<input type="hidden" value="${invalid}" id="invalid">

</body>

<script src="geoscript/geolist.js" charset="UTF-8"></script>

</html>