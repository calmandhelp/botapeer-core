package com.botapeer.usecase.plantRecord;

import java.util.Optional;

import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;
import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.text.Title;

public class PlantRecordResponseDto {
	public static Optional<PlantRecordResponse> toResponse(Optional<PlantRecord> record) {
		if (record.isPresent()) {
			PlantRecordResponse response = new PlantRecordResponse();
			response.setId(record.get().getId());
			response.setAlive(record.get().getAlive());
			Title title = record.get().getTitle();
			String t = title.getTitle();
			response.setTitle(t);
			response.setCreatedAt(record.get().getCreatedAt());
			response.setEndDate(record.get().getEndDate());
			response.setUpdatedAt(record.get().getUpdatedAt());
			return Optional.ofNullable(response);
		}
		return null;
	}

	//	public static Collection<PlantRecordResponse> toResponse(Collection<PlantRecordEntity> users) {
	//
	//		Collection<PlantRecordResponse> responses = new ArrayList<PlantRecordResponse>();
	//
	//		for (User user : users) {
	//			UserResponse r = new UserResponse();
	//			r.setId(user.getId());
	//			r.setEmail(user.getEmail());
	//
	//			UserName userName = user.getName();
	//			String strUserName = userName.getName();
	//			r.setName(strUserName);
	//
	//			r.setCoverImage(user.getCoverImage());
	//			r.setProfileImage(user.getProfileImage());
	//			r.setStatus(user.getStatus());
	//			r.setDescription(user.getDescription());
	//
	//			responses.add(r);
	//		}
	//
	//		return responses;
	//	}
}
