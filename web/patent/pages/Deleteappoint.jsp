<%-- 
    Document   : viewallappointment
    Created on : 20 Feb, 2017, 4:29:43 AM
    Author     : Rowdy
--%>

<%@page import="java.sql.*"%>
<%@page import="hlpr.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

  


</head>
<body>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Review</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                         All Review You Posted
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                                             <%
                                                 
                                                 
                                                 HttpSession sess=request.getSession();  
     String t= sess.getAttribute("id").toString();
       try 
 {
  Class.forName("com.mysql.jdbc.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","root");
       Statement st=con.createStatement();
        String sql="Select * from appointmentt where patid='"+t+"' ";
            
            ResultSet rs=st.executeQuery(sql);
        
            %>
                            <table width="100%" class="table table-striped table-bordered table-hover" id="epi">
                               <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Patid</th>
    
 <th>Country</th>
  <th>State</th>
   <th>City</th>
   <th>App Date</th>
    <th>App Time</th>
    <th>Status</th>
    <th>Delete</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    
                                                                    <%
            int x=0;
     while(rs.next())
                    {
                        
                        %>
                        
     
 <tr class="odd gradeX">
       <th><%=rs.getInt("id")%></th>
     <th><%=rs.getString("patid")%></th>
     <th><%=hlpr.Helper.getCountry(rs.getString("country"))%></th>
     <th><%=hlpr.stat.getState(rs.getString("state"))%></th>
     <th><%=hlpr.cty.getCity(rs.getString("city"))%></th>
   <th><%=rs.getString("appdate")%></th>
   <th><%=rs.getString("apptime")%></th>
     <th><%=rs.getString("status")%></th>
    
  
  <th><button value="<%="DDD-"+rs.getInt("id")%>" ><img src="../../images/deletee.png"  height="25" width="25"></button></th>
 </tr>
                                    <%
                     }
            con.close();
                        st.close();
                    
                    
                    }catch(Exception e)
                    {
                 out.println(e.getMessage());
                    }
        %>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
  

 





</body>
</html>