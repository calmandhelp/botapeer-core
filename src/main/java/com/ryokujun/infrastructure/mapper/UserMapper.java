package com.ryokujun.infrastructure.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ryokujun.domain.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	User findById(int id);

	@Select("SELECT * FROM users")
	Collection<User> findAll();

	@Update("UPDATE users "
			+ "SET "
			+ "name = #{name}, "
			+ "email = #{email}, "
			+ "pass = #{pass}, "
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
			+ "pass, "
			+ "status, "
			+ "updated_at, "
			+ "created_at "
			+ ") "
			+ "VALUES "
			+ "( "
			+ "#{name}, "
			+ "#{email}, "
			+ "#{pass}, "
			+ "#{status}, "
			+ "current_timestamp, "
			+ "current_timestamp "
			+ ")")
	boolean create(User user);

	@Select("SELECT * from users WHERE email = #{email}")
	User findByEmail(String email);
}
