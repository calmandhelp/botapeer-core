package com.botapeer.infrastructure.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.botapeer.infrastructure.entity.PostEntity;

@Mapper
public interface PostMapper {

	@Select("SELECT * FROM posts WHERE plant_record_id = #{id}")
	Collection<PostEntity> findByPlantRecordId(int id);

	//	@Insert("INSERT INTO labels ( "
	//			+ "name, "
	//			+ "plant_record_id, "
	//			+ "created_at, "
	//			+ "updated_at "
	//			+ ") "
	//			+ "VALUES ( "
	//			+ "#{name}, "
	//			+ "#{plantRecordId}, "
	//			+ "CURRENT_TIMESTAMP, "
	//			+ "CURRENT_TIMESTAMP "
	//			+ ");")
	//	boolean create(Label label);

}
