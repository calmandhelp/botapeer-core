package com.botapeer.infrastructure.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.botapeer.domain.model.Label;

@Mapper
public interface LabelMapper {

	@Insert("INSERT INTO labels ( "
			+ "name, "
			+ "plant_record_id, "
			+ "created_at, "
			+ "updated_at "
			+ ") "
			+ "VALUES ( "
			+ "#{name}, "
			+ "#{plantRecordId}, "
			+ "CURRENT_TIMESTAMP, "
			+ "CURRENT_TIMESTAMP "
			+ ");")
	boolean create(Label label);

}
