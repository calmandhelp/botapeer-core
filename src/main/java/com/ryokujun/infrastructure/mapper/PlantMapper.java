package com.ryokujun.infrastructure.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ryokujun.domain.entity.Plant;

public interface PlantMapper {
	@Select("SELECT * FROM plants WHERE id = #{id}")
	Plant findById(int id);

	@Select("SELECT * FROM plants")
	Collection<Plant> findAll();

	@Update("UPDATE plants "
			+ "SET "
			+ "title = #{title}, "
			+ "description = #{description}, "
			+ "imageUrl = #{imageUrl}, "
			+ "status = #{status}, "
			+ "alive = #{alive} "
			+ "WHERE id = #{id}")
	boolean update(Plant plant);

	@Delete("DELETE FROM plants "
			+ "WHERE id = #{plantId}")
	boolean delete(int plantId);

}
