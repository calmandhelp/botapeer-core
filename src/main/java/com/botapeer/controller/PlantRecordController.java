package com.botapeer.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.usecase.interfaces.IPlantRecordUsecase;

import api.PlantRecordsApi;
import lombok.RequiredArgsConstructor;
import model.CreatePlantRecordRequest;
import model.PlantRecordResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlantRecordController implements PlantRecordsApi {

	private final IPlantRecordUsecase plantUsecase;

	@Override
	@GetMapping("/plant_records/{plantRecordId}")
	public ResponseEntity<PlantRecordResponse> getPlantRecordById(@PathVariable String plantRecordId) {
		Optional<PlantRecordResponse> response = plantUsecase.findById(plantRecordId);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@Override
	@PostMapping("/plant_records")
	public ResponseEntity<PlantRecordResponse> createPlantRecord(
			@Validated @RequestBody CreatePlantRecordRequest request) {
		Optional<PlantRecordResponse> response = plantUsecase.create(request);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/plant_records/users/{userId}")
	public ResponseEntity<List<PlantRecordResponse>> getPlantRecordByUserId(@PathVariable String userId) {
		Collection<PlantRecordResponse> response = plantUsecase.findByUserId(userId);
		List<PlantRecordResponse> list = new ArrayList<>(response);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
