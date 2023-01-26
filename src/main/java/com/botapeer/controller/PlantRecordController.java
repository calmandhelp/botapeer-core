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
import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;
import com.botapeer.usecase.interfaces.IPlantRecordUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlantRecordController {

	private final IPlantRecordUsecase plantUsecase;

	@GetMapping("/plant_records/{plant_record_id}")
	public Optional<PlantRecordResponse> getPlantRecord(@PathVariable String plant_record_id) {
		Optional<PlantRecordResponse> response = plantUsecase.findById(plant_record_id);
		System.out.println(response);
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
