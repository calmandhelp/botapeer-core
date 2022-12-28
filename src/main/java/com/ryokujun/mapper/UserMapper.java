package com.ryokujun.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ryokujun.domain.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	User findById(String id);

	@Select("SELECT * FROM users")
	Collection<User> findAll();

	@Update("UPDATE users "
			+ "SET "
			+ "name = #{name}, "
			+ "email = #{email}, "
			+ "pass = #{pass} "
			+ "WHERE id = #{id}")
	boolean update(User user);

	@Delete("DELETE FROM users "
			+ "WHERE id = #{userId}")
	boolean delete(int userId);
}
