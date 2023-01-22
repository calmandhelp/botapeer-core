package com.botapeer.infrastructure.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Select;

import com.botapeer.infrastructure.entity.PlantRecordEntity;

public interface PlantRecordMapper {
	@Select("SELECT * FROM plant_records WHERE id = #{id}")
	Optional<PlantRecordEntity> findById(int id);

	//	@Select("SELECT * FROM plant_records")
	//	Collection<PlantRecordEntity> findAll();
	//
	//	@Select("SELECT * FROM plant_records WHERE 	user_id = #{userId}")
	//	Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	@Update("UPDATE plant_records "
	//			+ "SET "
	//			+ "category_id = #{categoryId}, "
	//			+ "user_id = #{userId}, "
	//			+ "title = #{title}, "
	//			+ "description = #{description}, "
	//			+ "image_url = #{imageUrl}, "
	//			+ "status = #{status}, "
	//			+ "alive = #{alive} "
	//			+ "WHERE id = #{id}")
	//	boolean update(PlantRecordEntity plant);
	//
	//	@Delete("DELETE FROM plant_records "
	//			+ "WHERE id = #{plantId}")
	//	boolean delete(int plantId);

}
