package controller.admin;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.AdminService;
import service.impl.AdminServicesImpl; 

public class AdminDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminService adminService = new AdminServicesImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	   
		String admin_id = req.getParameter("admin-id");
		adminService.delete(admin_id);	 
		resp.sendRedirect(req.getContextPath() + "/admin/admin/list"); 
	}
}

