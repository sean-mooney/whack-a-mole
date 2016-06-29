/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc224.login;

import com.mysql.jdbc.Connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */
public class LoginServlet extends HttpServlet {

    public static final String prpTxtUser = "fx.user.text.name";
    public static final String prpTxtPass = "fx.pass.text.name";
    public static final String dbDriver = "com.mysql.jdbc.Driver";
    public static final String dbUsername = "db.user.text.name";
    public static final String dbPassword = "db.pass.text.name";
    public static final String dbURL = "db.url";
    public static final String error = "errorMessage";
    public static final String usernameKey = "username";
    public static final String dbConnection = "db_connection";
    public static final String db_query_key = "db.query";
    
    private static final String prpUsersFileName = "fx.users.file.name";
    private static final String acceptedJSP = "fxCalc.jsp";
    private static final String deniedJSP = "login.jsp";
    private static final String errorMsg = "Invalid Login";
    private static final String errorMsg2 = "Error Processing Your Request";
    private static final String fxUsersKey = "fx_users";


    @Override
    public void init() {
        Properties FX_USERS = new Properties();
        ServletContext context = getServletContext();
        Connection connection = null;

        try {
            Class.forName(dbDriver);
            connection = (Connection) DriverManager.getConnection(context.getInitParameter(dbURL), context.getInitParameter(dbUsername), context.getInitParameter(dbPassword));
            context.setAttribute(dbConnection, connection);
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    @Override
    public void destroy() {
        ServletContext context = getServletContext();
        Connection connection = (Connection) context.getAttribute(dbConnection);
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String fwdUrl = deniedJSP;
        try {
            synchronized(this){
            Connection connection = (Connection) context.getAttribute(dbConnection);
            PreparedStatement preparedStatement = connection.prepareStatement(context.getInitParameter(db_query_key));
            
        
            String userUsername = request.getParameter(context.getInitParameter(prpTxtUser));
            String userPassword = request.getParameter(context.getInitParameter(prpTxtPass));

            preparedStatement.setString(1, userUsername);
            preparedStatement.setString(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                fwdUrl = acceptedJSP;
                request.getSession().setAttribute(usernameKey, userUsername);
            } else {
                request.setAttribute(error, errorMsg);
            }
            }
            RequestDispatcher dis = request.getRequestDispatcher(fwdUrl);
            dis.forward(request, response);
            
        
        } catch (Exception ex) {
            request.setAttribute(error, errorMsg2);
            RequestDispatcher dis = request.getRequestDispatcher(deniedJSP);
            dis.forward(request, response);
        }
    }
}
