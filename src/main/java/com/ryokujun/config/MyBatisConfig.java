package com.ryokujun.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ryokujun.infrastructure")
public class MyBatisConfig {

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return configuration -> configuration.setMapUnderscoreToCamelCase(true);
	}

	//	@Bean
	//	public DataSource dataSource() {
	//		return DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/my_gears_db").build();
	//	}
	//
	//	@Bean
	//	public SqlSessionFactory sqlSessionFactory() throws Exception {
	//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	//		factoryBean.setDataSource(dataSource());
	//		return factoryBean.getObject();
	//	}
	//
	//	@Bean
	//	public UserMapper userMapper() throws Exception {
	//		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
	//		return sqlSessionTemplate.getMapper(UserMapper.class);
	//	}

}
