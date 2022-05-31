package jp.co.solxyz.jsn.samples.web.methods.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.solxyz.jsn.samples.web.methods.entity.ChatEntity;

/**
 * チャット情報を操作するサーブレット
 * @author hikaru
 *
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet{

	private static final String JSP_PATH = "/WEB-INF/jsp/chat.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("data", ChatEntity.builder().build());
		
		req.getRequestDispatcher(JSP_PATH).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doDelete(req, resp);
	}
}
