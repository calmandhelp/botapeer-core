package com.botapeer.infrastructure.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.botapeer.domain.model.label.Label;

@Mapper
public interface LabelMapper {

	@Select("SELECT * FROM labels WHERE plant_record_id = #{id}")
	Collection<Label> findById(int id);

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
