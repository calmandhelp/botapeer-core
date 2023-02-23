package com.botapeer.infrastructure.mapper;

import java.util.Collection;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.botapeer.domain.model.place.Place;

@Mapper
public interface PlaceMapper {

	@Select("SELECT * FROM places")
	Collection<Place> findAll();

	@Select("SELECT * FROM places WHERE id = #{id}")
	Optional<Place> findById(Long id);

}
