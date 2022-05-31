package jp.co.solxyz.jsn.samples.web.methods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jp.co.solxyz.jsn.samples.web.methods.entity.ChatEntity;

public class DataDao {

	/**
	 * すべての情報を取得
	 * 
	 * @return 全データ
	 */
	public List<ChatEntity> getAll() {

		List<ChatEntity> list = new ArrayList<>();
		
		list.add(ChatEntity.builder().id(1).name("太郎").message("メッセージ1").build());
		list.add(ChatEntity.builder().id(1).name("次郎").message("メッセージ2").build());

		return list;
	}

	/**
	 * 単一情報の取得
	 * @param id 取得するデータのID
	 * @return 取得結果
	 */
	public Optional<ChatEntity> get(int id) {

		return 
			Optional.of(
			ChatEntity.builder().id(id).name("").message("").build());
	}

	/**
	 * 新規登録
	 * @param entity 登録データ
	 */
	public void insert(ChatEntity entity) {

	}

	/**
	 * 更新
	 * @param entity 更新データ
	 */
	public void update(ChatEntity entity) {

	}

	/**
	 * 削除
	 * @param id 削除するID
	 */
	public void delete(int id) {

	}
}
