package com.botapeer.infrastructure.mapper;

import java.util.Collection;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.botapeer.infrastructure.entity.UserEntity;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	Optional<UserEntity> findById(Long id);

	@Select("SELECT * FROM users")
	Collection<UserEntity> findAll();

	@Update("UPDATE users "
			+ "SET "
			+ "name = #{name}, "
			+ "email = #{email}, "
			+ "status = #{status}, "
			+ "profile_image = #{profileImage}, "
			+ "cover_image = #{coverImage} , "
			+ "description = #{description} "
			+ "WHERE id = #{id}")
	boolean update(UserEntity user);

	@Delete("DELETE FROM users "
			+ "WHERE id = #{userId}")
	boolean delete(Long userId);

	@Insert("INSERT INTO users"
			+ "( "
			+ "name, "
			+ "email, "
			+ "password, "
			+ "status, "
			+ "updated_at, "
			+ "created_at, "
			+ ") "
			+ "VALUES "
			+ "( "
			+ "#{name}, "
			+ "#{email}, "
			+ "#{password}, "
			+ "#{status}, "
			+ "current_timestamp, "
			+ "current_timestamp "
			+ ")")
	boolean create(UserEntity user);

	@Select("SELECT * from users WHERE email = #{usernameOrEmail} OR name = #{usernameOrEmail}")
	Optional<UserEntity> findUserByNameOrEmail(String usernameOrEmail);

	@Select("SELECT * from users WHERE email = #{email}")
	Optional<UserEntity> findByEmail(String email);

	@Select("SELECT * from users WHERE name = #{name}")
	Optional<UserEntity> findByName(String name);

	@Select("SELECT * from users "
			+ "WHERE CASE "
			+ "WHEN #{name} IS NULL THEN 1 = 1 "
			+ "ELSE name = #{name} END")
	Collection<UserEntity> findUsers(String name);
}
