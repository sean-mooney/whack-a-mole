<%-- 
    Document   : fxInit
    Created on : Oct 12, 2015, 8:53:12 PM
    Author     : Sean
--%>

<%@page import="java.sql.Connection"%>
<%@page import="com.csc224.login.LoginServlet"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Properties"%>
<%@page import="com.csc224.data.*"%>
<%@page import="com.csc224.data.FxBean"%>

<%!

        
        public void jspInit(){
        
        Properties fxProps = new Properties(); //Declare new Properties object
        ServletContext context = getServletContext(); //Declare new ServletContext object
        Connection connection = (Connection) context.getAttribute(LoginServlet.dbConnection);
        try {
        FileInputStream stream = new FileInputStream(context.getRealPath(context.getInitParameter(FxDataModel.prpFileName))); //Gets file name from web.xml context parameter
        fxProps.load(stream); //sets file from "stream" to fxProps so fxProps is no longer blank
        fxProps.setProperty( FxDataModel.ratesFileName, context.getRealPath(fxProps.getProperty(FxDataModel.ratesFileName))); //Sets property in file to the file path of the rates file

        stream.close(); //Closes stream
 
        }
        
        catch (IOException e) 
            {
                
            }
        
        context.setAttribute(FxDataModel.fxDataKey, new FxDataModel(fxProps, connection ));
        
        FxDataModel fxDataModel = (FxDataModel) context.getAttribute(FxDataModel.fxDataKey);
        context.setAttribute(FxBean.FX_CURRENCIES_KEY, fxDataModel.getCurrencies());
        
    }

%>