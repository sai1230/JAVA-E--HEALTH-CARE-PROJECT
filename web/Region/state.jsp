<%-- 
    Document   : state
    Created on : 20 Feb, 2017, 12:11:00 AM
    Author     : Rowdy
--%>

<%@page contentType="text/html" import="java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          
                             <select class="form-control" id="state" name="state" onchange="showCity(this.value)">
                            <option value="0">Please Select State</option>
                            <%
                            try
                            {    
                            
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","root");
                            Statement st=con.createStatement();
                            String sql="Select * from state where CountryId='"+request.getParameter("q")+"'";
                            ResultSet rs=st.executeQuery(sql);
                            while(rs.next())
                            {%>
                            
                            <option value="<%=rs.getInt(1)%>"><%= rs.getString(3)%></option>
                            <%}
                            rs.close();
                            con.close();
                            st.close();
                            
                            }catch(Exception e){}
                            
                            %>
                        </select>
    </body>
</html>
