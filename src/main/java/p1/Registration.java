package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class Registration extends HttpServlet {
	
	
	 PreparedStatement pst ;
	 
	 Connection con;
	 
	 Statement st;
	 ResultSet rs ;

	
    public Registration() {
    }
    
  
    
    
    public void init() throws ServletException {
    	    try {
   			Class.forName("com.mysql.cj.jdbc.Driver");
   			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","saket12345@");
    	    pst=con.prepareStatement("INSERT INTO registration (name, email, password) VALUES (?, ?, ?)");
    	    st=con.createStatement();
    	    }catch(Exception e) {}
    	     
    	  
    	  
    	
    }
    	
    	
    	
    	
 

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirm_password=request.getParameter("confirm_password");
	     
		try {
		//preparedStatement.setInt(1, 1);
		 pst.setString(1,name);
		 pst.setString(2, email);
		 pst.setString(3, password);
		 int val=pst.executeUpdate();
		 
		}catch(SQLException exp) {
			System.out.println(exp);
			System.out.println("Data not inserted Failed to insert data in to database ");
	
		}
		
		try {
			PrintWriter pw=response.getWriter();
			
		 rs =st.executeQuery("Select * From registration;");
		 while(rs.next()) {
			 System.out.println(rs.getInt(4)+"\n");
			 System.out.println(rs.getString(1)+"\n");
			 System.out.println(rs.getString(2)+"\n");
			 System.out.println(rs.getString(3)+"\n");
			 pw.println(rs.getInt(4)+"\n"+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n");
		 }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		 
	
		/*
		 * PrintWriter printwriter= response.getWriter();
		 * printwriter.println("Name : "+" "+name+" \n "+"password : "
		 * +password+" \n Email : "+confirm_password);
		 * 
		 * 
		 * System.out.println("Name :"+" "+name+"\npassword : "+password+" \n Email : "
		 * +confirm_password);
		 */
	
	
	
	}

}
