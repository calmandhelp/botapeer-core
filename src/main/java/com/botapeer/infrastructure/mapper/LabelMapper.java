package com.botapeer.infrastructure.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;

import com.botapeer.domain.model.Label;
import com.botapeer.infrastructure.entity.PlantRecordEntity;

public interface LabelMapper {

	@Insert("INSERT INTO labels ( "
			+ "name, "
			+ "plant_record_id, "
			+ "created_at "
			+ "updated_at "
			+ ") "
			+ "VALUES ( "
			+ "#{name}, "
			+ "#{plantRecordId}, "
			+ "CURRENT_TIMESTAMP, "
			+ "CURRENT_TIMESTAMP "
			+ ");")
	Optional<PlantRecordEntity> create(Label label);

}
