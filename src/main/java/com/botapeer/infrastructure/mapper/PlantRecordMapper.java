package com.botapeer.infrastructure.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.botapeer.infrastructure.entity.PlantRecord;

public interface PlantRecordMapper {
	@Select("SELECT * FROM plants WHERE id = #{id}")
	PlantRecord findById(int id);

	@Select("SELECT * FROM plants")
	Collection<PlantRecord> findAll();

	@Select("SELECT * FROM plants WHERE 	user_id = #{userId}")
	Collection<PlantRecord> findByUserId(int userId);

	@Update("UPDATE plants "
			+ "SET "
			+ "category_id = #{categoryId}, "
			+ "user_id = #{userId}, "
			+ "title = #{title}, "
			+ "description = #{description}, "
			+ "image_url = #{imageUrl}, "
			+ "status = #{status}, "
			+ "alive = #{alive} "
			+ "WHERE id = #{id}")
	boolean update(PlantRecord plant);

	@Delete("DELETE FROM plants "
			+ "WHERE id = #{plantId}")
	boolean delete(int plantId);

}
