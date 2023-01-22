package com.botapeer.infrastructure.repository.dto.plantRecord;

import java.util.Optional;

import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.text.Title;
import com.botapeer.infrastructure.entity.PlantRecordEntity;

public class PlantRecordRepositoryDto {
	public static Optional<PlantRecord> toModel(Optional<PlantRecordEntity> entity) {
		if (entity.isPresent()) {
			PlantRecord model = new PlantRecord();
			model.setId(entity.get().getId());
			String title = entity.get().getTitle();
			Title t = new Title(title);
			model.setTitle(t);
			model.setAlive(entity.get().getAlive());
			model.setCreatedAt(entity.get().getCreatedAt());
			model.setUpdatedAt(entity.get().getUpdatedAt());
			model.setEndDate(entity.get().getEndDate());
			return Optional.ofNullable(model);
		}
		return null;
	}

	//	public static Collection<User> toModel(Collection<UserEntity> users) {
	//
	//		List<UserEntity> userList = new ArrayList<>(users);
	//
	//		Collection<User> user = new ArrayList<User>();
	//
	//		for (UserEntity userEntity : userList) {
	//			User u = new User();
	//			u.setId(userEntity.getId());
	//			u.setEmail(userEntity.getEmail());
	//
	//			String strUserName = userEntity.getName();
	//			UserName userName = new UserName(strUserName);
	//			u.setName(userName);
	//
	//			u.setProfileImage(userEntity.getProfileImage());
	//			u.setCoverImage(userEntity.getCoverImage());
	//			u.setStatus(userEntity.getStatus());
	//			u.setDescription(userEntity.getDescription());
	//
	//			user.add(u);
	//		}
	//
	//		return user;
	//	}
	//
	//	public static UserEntity toEntity(User user) {
	//		UserEntity u = new UserEntity();
	//		u.setId(user.getId());
	//		u.setEmail(user.getEmail());
	//
	//		UserName userName = user.getName();
	//		String strUserName = userName.getName();
	//
	//		u.setName(strUserName);
	//		u.setProfileImage(user.getProfileImage());
	//		u.setCoverImage(user.getCoverImage());
	//		u.setStatus(user.getStatus());
	//		u.setDescription(user.getDescription());
	//		return u;
	//	}
}
