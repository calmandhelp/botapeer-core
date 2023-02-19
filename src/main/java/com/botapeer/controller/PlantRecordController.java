package com.botapeer.controller;

import java.security.Principal;
import java.util.Collection;
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
import com.botapeer.usecase.interfaces.IPlantRecordUsecase;

import lombok.RequiredArgsConstructor;
import model.PlantRecordResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlantRecordController {

	private final IPlantRecordUsecase plantUsecase;

	@GetMapping("/plant_records/{plantRecordId}")
	public Optional<PlantRecordResponse> getPlantRecord(@PathVariable String plantRecordId) {
		Optional<PlantRecordResponse> response = plantUsecase.findById(plantRecordId);
		return response;
	}

	@PostMapping("/plant_records")
	public Optional<PlantRecordResponse> createPlant(@Validated @RequestBody CreatePlantRecordRequest record,
			BindingResult result, Principal principal) {
		Optional<PlantRecordResponse> response = plantUsecase.create(record, result, principal);
		return response;
	}

	@GetMapping("/plant_records/users/{userId}")
	public Collection<PlantRecordResponse> findByUserId(@PathVariable String userId) {
		Collection<PlantRecordResponse> response = plantUsecase.findByUserId(userId);
		return response;
	}

}
