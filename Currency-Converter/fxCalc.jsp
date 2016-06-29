<%-- 
    Document   : fxCalc
    Created on : Sep 30, 2015, 12:13:37 PM
    Author     : Sean
--%>
<%@include file="fxInit.jsp"%>
<%@page import="com.csc224.login.LoginServlet"%>
<jsp:useBean id="fxBean" scope="page" class="com.csc224.data.FxBean" />
<jsp:setProperty name="fxBean" property="*" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%             
        
        FxDataModel fxDataModel = (FxDataModel) application.getAttribute(FxDataModel.fxDataKey); 
        Properties fxProps = fxDataModel.getFxProps();
        
        String srcAmtName = fxProps.getProperty(fxBean.SRC_AMNT_KEY);
        String srcCurrName = fxProps.getProperty(fxBean.SRC_CUCY_KEY);
        String dstAmtName = fxProps.getProperty(fxBean.DST_AMNT_KEY);
        String dstCurrName = fxProps.getProperty(fxBean.DST_CUCY_KEY);
        
        
        String convert;
        String username;
        String curr1 = fxBean.getSrcCucy();
        String curr2 = fxBean.getDstCucy();
        String amtIn = fxBean.getSrcAmt();
        
        username = (String) session.getAttribute(LoginServlet.usernameKey);
        if (username == null){
        pageContext.forward(fxDataModel.returnJSP);
        }
        
        String errorMessage = "";
        try {
        convert = ( Double.valueOf( fxDataModel.getFxRate(curr1,curr2)*Double.parseDouble( amtIn ) ) ).toString();
        }
        catch (NullPointerException ex){
            
            amtIn = "";
            convert = " ";
        }
        catch (NumberFormatException ex){ 
            errorMessage = "Please insert a valid number!";
            
            amtIn  = "";
            convert = "";

        }
        session.setAttribute("curr1", curr1);
        session.setAttribute("curr2", curr2);

%>

<html>
    <head>
        <title>'F/X Calculator'</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    
    <style>
        
        p {color: red; text-align: center;}
        
        fieldset {display: inline-block; padding: 10px; box-sizing: border-box; vertical-align: text-top;}

        
    </style>
        
    <body>
        <div align="center">
        <h2>Money Banks F/X Calculator</h2><hr><br>
        <h3>Welcome ${username}!</h3>
        
            
    <fieldset>
    <legend>Source Currency:</legend>
    <form action="fxCalc.jsp" method="post" name="fxCalc">
      
    <select name="<%=srcCurrName%>">
        <c:forEach var="inCurrency" items="${ applicationScope[fxBean.fxCurrenciesKey]}">
            <option value="${inCurrency}"
            <c:if test ='${inCurrency == curr1}'>selected</c:if>>${inCurrency}</option>
        </c:forEach>
    </select>
            
      <input name="<%=srcAmtName%>" value = <%=amtIn%>>
    </fieldset>
        
    
    <fieldset>
            <legend>Destination Currency:</legend>
            
    <select name="<%=dstCurrName%>">
      <c:forEach var="outCurrency" items="${ applicationScope[fxBean.fxCurrenciesKey]}">
        <option value="${outCurrency}" <c:if test ='${outCurrency == curr2}'>selected</c:if>>${outCurrency}</option>
      </c:forEach>
    </select>
      
      <input name="$<%=dstAmtName%>" disabled value = <%=convert%> ><br><br>
    </fieldset>
        
        
    <br><br>
        <input type="submit" value="Convert">
        <input type="reset" value="Reset"><br>
        <% if(errorMessage != null){
            %> <p><%= errorMessage %> </p>
        <% }    
            %>
        </div>
    </form>
    </body>
</html>