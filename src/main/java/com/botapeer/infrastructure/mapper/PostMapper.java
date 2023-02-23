package com.botapeer.infrastructure.mapper;

import java.util.Collection;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.botapeer.infrastructure.entity.PostEntity;

@Mapper
public interface PostMapper {

	@Select("SELECT * FROM posts WHERE id = #{id}")
	Optional<PostEntity> findById(Long id);

	@Select("SELECT * FROM posts WHERE plant_record_id = #{id}")
	Collection<PostEntity> findByPlantRecordId(Long id);

	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	@Insert("INSERT INTO posts ( "
			+ "title, "
			+ "plant_record_id, "
			+ "article, "
			+ "image_url, "
			+ "status, "
			+ "created_at, "
			+ "updated_at "
			+ ") "
			+ "VALUES ( "
			+ "#{title}, "
			+ "#{plantRecordId}, "
			+ "#{article}, "
			+ "#{imageUrl}, "
			+ "#{status}, "
			+ "CURRENT_TIMESTAMP, "
			+ "CURRENT_TIMESTAMP "
			+ ");")
	boolean create(PostEntity post);

	@Select("SELECT * FROM posts WHERE "
			+ "plant_record_id = #{plantRecordId} AND "
			+ "id = #{postId}")
	Optional<PostEntity> getPostByIdAndPlantRecordId(Long plantRecordId, Long postId);

	@Delete("DELETE FROM posts WHERE "
			+ "plant_record_id = #{plantRecordId} AND "
			+ "id = #{postId}")
	boolean delete(Long plantRecordId, Long postId);

}
