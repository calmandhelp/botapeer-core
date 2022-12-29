package com.ryokujun.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ryokujun.constants.ResponseConstants;
import com.ryokujun.controller.exception.validation.ErrorMessages;
import com.ryokujun.controller.exception.validation.ValidationException;
import com.ryokujun.domain.entity.Plant;
import com.ryokujun.domain.service.IPlantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlantController {

	private final IPlantService plantService;

	@GetMapping("plants/{plantId}")
	public Plant getPlant(@PathVariable String plantId) {
		try {
			int plantIdForget = Integer.parseInt(plantId);
			Plant p = plantService.findById(plantIdForget);
			return p;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@PostMapping("plants/{plantId}")
	public Plant updatePlant(@PathVariable String plantId, @Validated @RequestBody Plant plant,
			BindingResult result) {
		if (result.hasErrors()) {
			List<HashMap<String, String>> list = new ArrayList<>();
			for (int i = 0; i < result.getErrorCount(); i++) {
				HashMap<String, String> errorsList = new HashMap<>();
				errorsList.put(ResponseConstants.ERRORS_CODE_KEY, result.getAllErrors().get(i).getCode());
				errorsList.put(ResponseConstants.ERRORS_MESSAGE_KEY, result.getAllErrors().get(i).getDefaultMessage());
				list.add(errorsList);
			}
			ErrorMessages errorMessages = new ErrorMessages();
			errorMessages.setMessages(list);
			throw new ValidationException(errorMessages);
		}
		try {
			int plantIdforUpdate = Integer.parseInt(plantId);
			plant.setId(plantIdforUpdate);
			if (!plantService.update(plant)) {
				throw new Error();
			}
			Plant p = plantService.findById(plantIdforUpdate);
			return p;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@DeleteMapping("plants/{plantId}")
	public void deletePlant(@PathVariable String plantId) {
		try {
			int id = Integer.parseInt(plantId);
			if (!plantService.delete(id)) {
				throw new Error();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@GetMapping("plants/users/{userId}")
	public Collection<Plant> getUserPlantList(@PathVariable String userId) {
		try {
			int userIdforget = Integer.parseInt(userId);
			Collection<Plant> plants = plantService.findByUserId(userIdforget);
			return plants;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
