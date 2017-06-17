
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegPg extends HttpServlet 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        out.println("<html><head><title>");
        out.println("Registration form");
        out.println("</title></head>");
        out.println("<body>");
        out.println("<form method=" + "post" + " action=" + "Register" + ">");
        out.println("Name:<input type="+ "text"+" name="+"name"+" /><br/>");
        out.println("Email ID:<input type="+"text"+" name="+"email"+" /><br/>");
        out.println("Password:<input type="+"text"+" name="+"pass"+" /><br/>");
        out.println("<input type="+"submit"+" value="+"Register"+" />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

       


	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        
        try{
        	
        	if(name == "" || email == "" || pass == ""){
        		
        		out.println("<html><body><script type=\"text/javascript\">");
	        	out.println("window.alert('Please fill in all the fields');");
	        	out.println("</script></body></html>");
        	}
        	
        	else{
            
		        //loading drivers for mysql
		        Class.forName("com.mysql.jdbc.Driver");
		
			//creating connection with the database 
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STUD", "root", "*a0669968990p*");
		        
		       
			        
			       PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS count FROM student WHERE email= '"+email+"'");
			        ResultSet rs = ps.executeQuery();
			        rs.next();
			        int tot = rs.getInt("count");
			      
			          if(tot == 0 )
			          {
			        	  Statement st = con.createStatement();
			        	    int i = st.executeUpdate("INSERT INTO student (name, email, pass) VALUES ('"+name+"', '"+email+"', '"+pass+"')");
			        	 
			        	    if(i>0){
			        		  out.println("<html><body><script type=\"text/javascript\">");
				        	  out.println("window.alert('You have successfully been registered');");
				        	  out.println("</script></body></html>");
			        	  }
				        	  
				    
			          }
			          else if(tot > 0){
			        	  out.println("<html><body><script type=\"text/javascript\">");
			        	  out.println("window.alert('This email id is already in use');");
			        	  out.println("</script></body></html>");
			          }
			          
			         
        	} 
        	
        	 RegPg obj = new RegPg();
	         obj.doGet(request, response);
                 
	      
	        
    }
    catch(Exception se)
    {
        se.printStackTrace();
    }
		
	}
}

