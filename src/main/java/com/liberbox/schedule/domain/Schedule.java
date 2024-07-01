package com.liberbox.schedule.domain;

import com.liberbox.audit.repository.Auditable;
import com.liberbox.config.domain.ToEntity;
import com.liberbox.user.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Schedule extends ToEntity implements Auditable {

	@Id
	private String id;

	private String name;

	private LocalTime startTime;

	private LocalTime endTime;

	private Schedule(String name,LocalTime startTime, LocalTime endTime) {
		this.id = UUID.randomUUID().toString();
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;

	}

	public static Schedule to(String name,LocalTime startTime, LocalTime endTime) {
		return new Schedule(name, startTime,endTime);
	}

	@Override
	public String getEntityId() {
		return this.id;
	}

	@Override
	public String getEntityName() {
		return getClass().getSimpleName();
	}

	public Schedule toUpdated(String name, LocalTime startTime, LocalTime endTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		return this;
	}
}
