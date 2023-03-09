package eecs4413.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static int numberOfUsers=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pwriter = response.getWriter();
		
		
		String name = request.getParameter("name");
        String password = request.getParameter("pass");
        //additions
        String email = request.getParameter("email");
        
//        pwriter.print("Hello "+name);
//        pwriter.print(" your password is: "+password + "");
//        pwriter.print(" your email is : "+email + "");
//        HttpSession session=request.getSession();
//        
//        User userBean= (User)session.getAttribute("UserBean");
//        if(userBean==null) {
//        	userBean = new User();
//        }
//        userBean.setFirstName(name);
//        userBean.setPassword(password);
//        userBean.setEmail(email);
//        session.setAttribute("uname", userBean.getFirstName());
//        session.setAttribute("upass", userBean.getPassword());
//        session.setAttribute("email", userBean.getEmail());
//        session.setAttribute("totalusers", numberOfUsers);
        
        int id = numberOfUsers;
        numberOfUsers++;
        System.out.println(id);
     
        RequestDispatcher dispatcher =null;
        Connection conn =null;
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false","root","password");
			PreparedStatement pre = conn.prepareStatement("insert into user_table(id,name,email,password) values(?,?,?,?) ");
			pre.setInt(1, id);
			pre.setString(2, name);
			pre.setString(3, email);
			pre.setString(4, password);
			
			
			int rowcount = pre.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
			if(rowcount>0) {
				request.setAttribute("status", "success");
				//pwriter.write("account created");
			}
			else {
				request.setAttribute("status", "failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
	}

}
