/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rowdy
 */
@WebServlet(name = "adminlogin", urlPatterns = {"/adminlogin"})
public class adminlogin extends HttpServlet {


   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

           
           String uname = request.getParameter("uname");
           
            String pass=request.getParameter("pass");
        out.println(uname+""+pass);
                
                
            //String destination=request.getParameter("destination");

          Class.forName("com.mysql.jdbc.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","root");
       Statement st=con.createStatement();
      //  Statement st2=con.createStatement();
     
       String sql="Select * from admin where id='"+uname+"' and password='"+pass+"'";
       
       
       
           
            ResultSet rs=st.executeQuery(sql);
            
              //String slq="Update expert set status='"+"AA"+"' where id='"+uname+"'";
           // int p=st2.executeUpdate(slq);
            
            
            
            rs.next();
            if(rs.getRow()>0)
          {
          HttpSession ses=request.getSession();
             ses.setAttribute("id",rs.getString(1));
              response.sendRedirect("http://localhost:8080/HealthProject/Admin/pages/AdminMasterpage.jsp?msglogin=You Have Login Successfully");
            }else{
              response.sendRedirect("Mainpage.jsp?msglogin=Invalid Username And Password");
            }
                
                    }
        
      
        catch(Exception e)
                    {
                    System.out.println(e.getMessage());
                    }
        }
    }
