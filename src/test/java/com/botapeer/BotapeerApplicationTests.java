package com.botapeer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.botapeer.controller.PlantRecordController;
import com.botapeer.controller.UserController;
import com.botapeer.domain.repository.IUserRepository;
import com.botapeer.domain.service.interfaces.IPlantRecordService;
import com.botapeer.usecase.interfaces.IPlantRecordUsecase;
import com.botapeer.usecase.interfaces.IUserUsecase;

@SpringBootTest
class BotapeerApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private IUserUsecase userUsecase;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PlantRecordController plantRecordController;

	@Autowired
	private IPlantRecordUsecase plantRecordUsecase;

	@Autowired
	private IPlantRecordService plantRecordService;

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(userUsecase).isNotNull();
		assertThat(userRepository).isNotNull();
		assertThat(plantRecordController).isNotNull();
		assertThat(plantRecordUsecase).isNotNull();
		assertThat(plantRecordService).isNotNull();
	}

}
