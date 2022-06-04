package jp.co.solxyz.jsn.samples.web.methods.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.solxyz.jsn.samples.web.methods.dao.DataDao;
import jp.co.solxyz.jsn.samples.web.methods.entity.ChatEntity;

/**
 * チャット情報を操作するサーブレット
 *
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

	/** Forward先のJSP */
	private static final String JSP_PATH = "/WEB-INF/jsp/chat.jsp";

	/** DAO */
	private final DataDao dataDao;

	/**
	 * コンストラクタ
	 */
	public ChatServlet() {
		// DAOの初期化
		this.dataDao = new DataDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int dataId = Integer.parseInt(req.getParameter("id"));

		req.setAttribute("data", this.dataDao.get(dataId));

		req.getRequestDispatcher(JSP_PATH).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String _id = (String) req.getParameter("id");
		String _name = (String) req.getParameter("name");
		String _message = (String) req.getParameter("message");

		// TODO: 必要に応じてバリデーションを設定しよう

		// POSTされたデータの取得
		ChatEntity receiveData = ChatEntity.builder().id(Integer.parseInt(_id)).name(_name).message(_message).build();
		try {
			this.dataDao.update(receiveData);
			resp.sendRedirect("./");
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().write(e.getMessage());
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// パラメータからIDの情報を取得
		String _id = req.getParameter("id");
		int dataId = Integer.parseInt(_id);

		// 削除処理
		try {
			this.dataDao.delete(dataId);

			// "success"という文字列を返却
			resp.getWriter().write("success");

		} catch (SQLException e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().write(e.getMessage());
		}

	}
}
