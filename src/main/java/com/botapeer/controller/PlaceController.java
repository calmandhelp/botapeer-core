package com.botapeer.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.domain.model.place.Place;
import com.botapeer.usecase.interfaces.IPlaceUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlaceController {

	private final IPlaceUsecase placeUsecase;

	@GetMapping("/places")
	public Collection<Place> getPlantRecord() {
		Collection<Place> response = placeUsecase.findAll();
		return response;
	}

	//	@PostMapping("/plant_records")
	//	public Optional<PlantRecordResponse> createPlant(@Validated @RequestBody CreatePlantRecordRequest record,
	//			BindingResult result, Principal principal) {
	//		Optional<PlantRecordResponse> response = plantUsecase.create(record, result, principal);
	//		return response;
	//	}

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
