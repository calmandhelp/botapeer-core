package com.botapeer.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.usecase.interfaces.IPlaceUsecase;

import api.PlacesApi;
import lombok.RequiredArgsConstructor;
import model.PlaceResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlaceController implements PlacesApi {

	private final IPlaceUsecase placeUsecase;

	@Override
	@GetMapping("/places")
	public ResponseEntity<List<PlaceResponse>> getPlaces() {
		Collection<PlaceResponse> response = placeUsecase.findAll();
		List<PlaceResponse> list = new ArrayList<>(response);
		return new ResponseEntity<>(list, HttpStatus.OK);
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
