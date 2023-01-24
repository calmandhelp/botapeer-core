package com.botapeer.infrastructure.mapper;

import java.util.Collection;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.botapeer.infrastructure.entity.PlantRecordEntity;

@Mapper
public interface PlantRecordMapper {
	@Select("SELECT * FROM plant_records WHERE id = #{id}")
	Optional<PlantRecordEntity> findById(int id);

	@Insert("INSERT INTO plant_records ( "
			+ "user_id, "
			+ "title, "
			+ "alive, "
			+ "status, "
			+ "created_at, "
			+ "updated_at "
			+ ") "
			+ "VALUES ( "
			+ "#{userId}, "
			+ "#{title}, "
			+ "#{alive}, "
			+ "#{status}, "
			+ "CURRENT_TIMESTAMP, "
			+ "CURRENT_TIMESTAMP "
			+ ");")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	boolean create(PlantRecordEntity entity);

	@Select("SELECT * FROM plant_records WHERE user_id = #{userId}")
	Collection<PlantRecordEntity> findByUserId(Long userId);

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
