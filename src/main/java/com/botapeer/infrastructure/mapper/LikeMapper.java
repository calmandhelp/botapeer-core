package com.botapeer.infrastructure.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.botapeer.infrastructure.entity.LikeCountPostEntity;

@Mapper
public interface LikeMapper {
	@Insert("INSERT INTO likes "
			+ "( "
			+ "user_id, "
			+ "post_id, "
			+ "created_at, "
			+ "updated_at "
			+ ") "
			+ "VALUES "
			+ "( "
			+ "#{userId} , "
			+ "#{postId}, "
			+ "CURRENT_TIMESTAMP, "
			+ "CURRENT_TIMESTAMP "
			+ ")")
	boolean createLikeToPost(Long postId, Integer userId);

	@Insert("DELETE FROM likes WHERE "
			+ "user_id = #{userId} "
			+ "AND "
			+ "post_id = #{postId}")
	boolean deleteLikeToPost(Long postId, Integer userId);

	@Select("SELECT EXISTS (SELECT 1 FROM likes WHERE "
			+ "user_id = #{userId} "
			+ "AND "
			+ "post_id = #{postId} "
			+ ") "
			+ "AS result")
	boolean isLikeWithPost(Long postId, Integer userId);

	@Select("SELECT post_id, "
			+ "COUNT(post_id) count "
			+ "FROM likes "
			+ "GROUP BY "
			+ "post_id "
			+ "HAVING post_id = #{postId}")
	Optional<LikeCountPostEntity> CountLikeWithPost(Long postId);

}