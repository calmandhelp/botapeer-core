package com.botapeer.usecase.plantRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.botapeer.controller.payload.label.LabelResponse;
import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;
import com.botapeer.domain.model.Label;
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

			Collection<LabelResponse> labels = new ArrayList<>();
			for (Label label : record.get().getLabels()) {
				LabelResponse labelResponse = new LabelResponse(label.getName());
				labels.add(labelResponse);
			}
			response.setLabels(labels);
			return Optional.ofNullable(response);
		}
		return null;
	}

	public static Collection<PlantRecordResponse> toResponse(Collection<PlantRecord> models) {

		Collection<PlantRecordResponse> responses = new ArrayList<PlantRecordResponse>();

		for (PlantRecord model : models) {
			PlantRecordResponse r = new PlantRecordResponse();
			r.setId(model.getId());
			r.setAlive(model.getAlive());
			r.setCreatedAt(model.getCreatedAt());
			r.setEndDate(model.getEndDate());
			Title title = model.getTitle();
			r.setTitle(title.getTitle());
			r.setUpdatedAt(model.getUpdatedAt());
			Collection<Label> labels = model.getLabels();
			Collection<LabelResponse> labelResponses = new ArrayList<>();
			for (Label l : labels) {
				LabelResponse labelResponse = new LabelResponse(l.getName());
				labelResponses.add(labelResponse);
			}
			r.setLabels(labelResponses);

			responses.add(r);
		}

		return responses;
	}
}
