<%-- 
    Document   : login
    Created on : Oct 20, 2015, 12:02:55 AM
    Author     : Sean
--%>

<%@page import="com.csc224.login.LoginServlet"%>

<%
        String userText = application.getInitParameter(LoginServlet.prpTxtUser);
        String passText = application.getInitParameter(LoginServlet.prpTxtPass);
        String error = (String) request.getAttribute(LoginServlet.error);
%>

<html>
    <head>
        <title>'F/X Login'</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    
    <style> 
        
    h2 {font-style: italic;} 
    p {color : red;}
    
    </style>
        
    <body>
    <h2 align="center">Money Banks Login</h2><hr><br>
    
    <form name="login" action="loginServlet" method="post">
        <div align="center">
            <body>Username: </body>
            <input type="text" name="<%=userText%>"><br><br>
            <body>Password: </body>
            <input type="password" name="<%=passText%>"><br><br>
        <input type="Submit" value="Login">
        <input type="Reset" value="Reset"><br>
        <% if(error != null){
            %> <p><%= error %> </p>
        <% }    
            %>
        </div>
    </form>
    </body>
</html>