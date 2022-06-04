package jp.co.solxyz.jsn.samples.web.methods.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jp.co.solxyz.jsn.samples.web.methods.entity.ChatEntity;

/**
 * チャットデータのDB操作を行うDAO
 * 
 */
public class DataDao {

	/** Database URL */
	private static final String URL = "jdbc:mysql://localhost:3306/chat";
	/** Database Access User */
	private static final String DBUSER = "root";
	/** Database Access Password */
	private static final String DBPASS = "myadmin";

	/**
	 * コンストラクタ
	 */
	public DataDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			// このエラーが発生したときは続行不能エラーのため処理を中断させる
			throw new RuntimeException(e);
		}
	}

	/**
	 * コネクション生成
	 * @return コネクション
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(URL, DBUSER, DBPASS);
		con.setAutoCommit(false);
		return con;
	}

	/**
	 * ResultSetをEntityに変換する
	 * @param resultSet DB実行結果の行情報
	 * @return 変換されたEntityデータ
	 * @throws SQLException DB情報の取得に失敗した際に発生するエラー
	 */
	private ChatEntity convertRStoEntity(ResultSet resultSet) throws SQLException {
		return ChatEntity.builder().id(resultSet.getInt("id")).name(resultSet.getString("name"))
				.message(resultSet.getString("message"))
				.posted(
						
						LocalDateTime.ofInstant(resultSet.getTimestamp("posted").toInstant(), ZoneId.systemDefault())
				).build();
	}

	/**
	 * すべての情報を取得
	 * 
	 * @return 全データ
	 */
	public List<ChatEntity> getAll() throws SQLException {

		String sql = "SELECT id, name, message, posted FROM chatlog";

		try (var con = this.getConnection()) {
			ResultSet resultSet = con.prepareStatement(sql).executeQuery();

			List<ChatEntity> list = new ArrayList<>();

			while (resultSet.next()) {
				// リスト
				list.add(convertRStoEntity(resultSet));
			}

			return list;
		}

	}

	/**
	 * 単一情報の取得
	 * 
	 * @param id 取得するデータのID
	 * @return 取得結果
	 */
	public Optional<ChatEntity> get(int id) {

		String sql = "SELECT id, name, message, posted FROM chatlog WHERE id = ?";

		try (var con = this.getConnection()) {
			var ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				return Optional.of(this.convertRStoEntity(resultSet));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}

	}

	/**
	 * 新規登録
	 * 
	 * @param entity 登録データ
	 */
	public void insert(ChatEntity entity) throws SQLException {

		String sql = "INSERT INTO chatlog (id, name, message) VALUES (?, ?, ?)";

		try (var con = this.getConnection()) {
			var ps = con.prepareStatement(sql);

			ps.setInt(1, entity.getId());
			ps.setString(2, entity.getName());
			ps.setString(3, entity.getMessage());

			if (ps.executeUpdate() > 0) {
				con.commit();
			}
		}

	}

	/**
	 * 更新
	 * 
	 * @param entity 更新データ
	 */
	public void update(ChatEntity entity) throws SQLException {

		String sql = "UPDATE chatlog SET name=?, message=? WHERE id=?";

		try (var con = this.getConnection()) {
			var ps = con.prepareStatement(sql);

			ps.setString(1, entity.getName());
			ps.setString(2, entity.getMessage());
			ps.setInt(3, entity.getId());

			if (ps.executeUpdate() > 0) {
				con.commit();
			}
		}
	}

	/**
	 * 削除
	 * 
	 * @param id 削除するID
	 */
	public void delete(int id) throws SQLException {

		String sql = "DELETE FROM chatlog where id=?";

		try (var con = this.getConnection()) {
			var ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			if (ps.executeUpdate() > 0) {
				con.commit();
			}
		}
	}
}
