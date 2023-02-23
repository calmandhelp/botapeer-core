package com.botapeer.infrastructure.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {
	@Insert("INSERT INTO likes "
			+ "( "
			+ "user_id, "
			+ "post_id, "
			+ "plant_record_id "
			+ ") "
			+ "VALUES "
			+ "( "
			+ "#{userId} , "
			+ "#{postId}, "
			+ "#{plantRecordId} "
			+ ")")
	boolean createLikeToPost(String plantRecordId, String postId, String userId);

	@Select("SELECT EXISTS (SELECT 1 FROM likes WHERE "
			+ "user_id = #{userId} "
			+ "AND "
			+ "post_id = #{postId} "
			+ "AND "
			+ "plant_record_id = #{plantRecordId} "
			+ ") "
			+ "AS result")
	boolean isLike(String plantRecordId, String postId, String userId);
}