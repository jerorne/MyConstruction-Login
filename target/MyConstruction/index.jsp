<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="username">Usuario:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="password">Contrase�a:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>

    <% 
    String error = request.getParameter("error");
    if ("true".equals(error)) {
    %>
        <p style="color: red;">Usuario o Contrase�a inv�lido, por favor intente de nuevo.</p>
    <% 
    } 
    %>
</body>
</html>
