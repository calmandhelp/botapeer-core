package com.ryokujun.infrastructure.mapper;

import java.util.Collection;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ryokujun.domain.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	Optional<User> findById(int id);

	@Select("SELECT * FROM users")
	Collection<User> findAll();

	@Update("UPDATE users "
			+ "SET "
			+ "name = #{name}, "
			+ "email = #{email}, "
			+ "password = #{password}, "
			+ "status = #{status} "
			+ "WHERE id = #{id}")
	boolean update(User user);

	@Delete("DELETE FROM users "
			+ "WHERE id = #{userId}")
	boolean delete(int userId);

	@Insert("INSERT INTO users"
			+ "( "
			+ "name, "
			+ "email, "
			+ "password, "
			+ "status, "
			+ "updated_at, "
			+ "created_at "
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
	boolean create(User user);

	@Select("SELECT * from users WHERE email = #{email}")
	Optional<User> findByEmail(String email);

	@Select("SELECT * from users WHERE name = #{name}")
	Optional<User> findByName(String name);
}
