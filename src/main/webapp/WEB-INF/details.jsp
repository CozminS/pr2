<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Movie details</title>
</head>
<body>



<div id ="result">


        <img src="imagini/<%=request.getParameter("urlp")%>" alt="un ban">
<br>
    <p>Trailer</p>
    <iframe width="420" height="315"
            src="<%=request.getParameter("urly")%>">
    </iframe>

</div>

<script>

   getYouTubeLink();

</script>
</body>
</html>