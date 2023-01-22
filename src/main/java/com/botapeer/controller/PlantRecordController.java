package com.botapeer.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;
import com.botapeer.usecase.IPlantRecordUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlantRecordController {

	private final IPlantRecordUsecase plantUsecase;

	@GetMapping("records/{recordId}")
	public Optional<PlantRecordResponse> getPlantRecord(@PathVariable String recordId) {
		Optional<PlantRecordResponse> response = plantUsecase.findById(recordId);
		return response;
	}

	//	@PostMapping("plants/{plantId}")
	//	public PlantRecordEntity updatePlant(@PathVariable String plantId, @Validated @RequestBody PlantRecordEntity plant,
	//			BindingResult result) {
	//
	//		return null;
	//	}
	//
	//	@DeleteMapping("plants/{plantId}")
	//	public void deletePlant(@PathVariable String plantId) {
	//		try {
	//			int id = Integer.parseInt(plantId);
	//			if (!plantService.delete(id)) {
	//				throw new Error();
	//			}
	//		} catch (Exception e) {
	//			System.out.println(e);
	//		}
	//	}
	//
	//	@GetMapping("plants/users/{userId}")
	//	public Collection<PlantRecordEntity> getUserPlantList(@PathVariable String userId) {
	//		try {
	//			int userIdforget = Integer.parseInt(userId);
	//			Collection<PlantRecordEntity> plants = plantService.findByUserId(userIdforget);
	//			return plants;
	//		} catch (Exception e) {
	//			System.out.println(e);
	//		}
	//		return null;
	//	}

}
