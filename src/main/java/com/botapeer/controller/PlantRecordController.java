package com.botapeer.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.controller.payload.plantRecord.CreatePlantRecordRequest;
import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;
import com.botapeer.usecase.IPlantRecordUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlantRecordController {

	private final IPlantRecordUsecase plantUsecase;

	@GetMapping("plant_records/{plant_record_id}")
	public Optional<PlantRecordResponse> getPlantRecord(@PathVariable String plant_record_id) {
		Optional<PlantRecordResponse> response = plantUsecase.findById(plant_record_id);
		return response;
	}

	@PostMapping("plant_records")
	public Optional<PlantRecordResponse> createPlant(@Validated @RequestBody CreatePlantRecordRequest record,
			BindingResult result, Principal principal) {
		System.out.println("test");
		Optional<PlantRecordResponse> response = plantUsecase.create(record, result, principal);
		return response;
	}

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
