package com.botapeer.controller;

import java.util.Collection;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.infrastructure.entity.PlantRecord;
import com.botapeer.domain.service.IPlantRecordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlantRecordController {

	private final IPlantRecordService plantService;

	@GetMapping("records/{recordId}")
	public PlantRecord getPlant(@PathVariable String plantId) {

		return null;
	}

	@PostMapping("plants/{plantId}")
	public PlantRecord updatePlant(@PathVariable String plantId, @Validated @RequestBody PlantRecord plant,
			BindingResult result) {

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
	public Collection<PlantRecord> getUserPlantList(@PathVariable String userId) {
		try {
			int userIdforget = Integer.parseInt(userId);
			Collection<PlantRecord> plants = plantService.findByUserId(userIdforget);
			return plants;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
