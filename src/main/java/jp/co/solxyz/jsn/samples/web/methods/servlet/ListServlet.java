package jp.co.solxyz.jsn.samples.web.methods.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.solxyz.jsn.samples.web.methods.dao.DataDao;

@WebServlet("/")
public class ListServlet extends HttpServlet {

	private static final String JSP_PATH = "/WEB-INF/jsp/index.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DataDao dao = new DataDao();
		
		// 
		req.setAttribute("data", dao.getAll());

		req.getRequestDispatcher(JSP_PATH).forward(req, resp);
	}
}
