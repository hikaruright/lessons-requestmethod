package jp.co.solxyz.jsn.samples.web.methods.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * チャット情報のデータエンティティ
 *
 */
@Data
@Builder
public class ChatEntity {
	
	/** 固有ID */
	private int id;
	
	/** 氏名 */
	private String name;
	
	/** メッセージ */
	private String message;

	/** 投稿日 */
	private LocalDateTime posted;
}
