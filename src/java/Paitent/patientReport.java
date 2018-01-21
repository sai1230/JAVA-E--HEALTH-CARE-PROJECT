/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paitent;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Rowdy
 */
@WebServlet(name = "patientReport", urlPatterns = {"/patientReport"})
@MultipartConfig(maxFileSize = 1617721588)
public class patientReport extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
 
  HttpSession sess=request.getSession();  
      String t= sess.getAttribute("id").toString();
          
            String topic  =request.getParameter("topic");
            String coment=request.getParameter("coment");
             String images=request.getParameter("images");
             
           
              InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("images");
        if (filePart != null) {
            // prints out some information for debugging
           out.println(filePart.getName()+"<br>");
           out.println(filePart.getSize()+"<br>");
           out.println(filePart.getContentType()+"<br>");
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try{        
                
            //String destination=request.getParameter("destination");

          Class.forName("com.mysql.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","root");
       
         String sql ="Insert into report(patid,prblm,descr,status,images) values (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,t);
           
            statement.setString(2,topic);
            statement.setString(3,coment);
            statement.setString(4,"Pending");
            
             if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(5, inputStream);
            }
             
  int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
                
          //       response.sendRedirect("MainHome.jsp");
                    }catch (Exception ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                   response.sendRedirect("http://localhost:8080/HealthProject/patent/pages/PatientMasterpage.jsp");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
         out.println(message);
        
        }
    }
}
