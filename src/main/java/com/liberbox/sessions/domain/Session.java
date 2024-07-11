package com.liberbox.sessions.domain;

import com.liberbox.audit.repository.Auditable;
import com.liberbox.config.domain.ToEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Session extends ToEntity implements Auditable {

    @Id
    private String id;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private String legendId;

    private String scheduleId;

    private Session(String title, LocalDateTime startTime, LocalDateTime endTime, String description, String legendId, String scheduleId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.legendId = legendId;
        this.scheduleId = scheduleId;
    }

    public static Session to(String title, LocalDateTime startTime, LocalDateTime endTime, String description, String legendId, String scheduleId) {
        return new Session(title, startTime, endTime, description, legendId, scheduleId);
    }

    @Override
    public String getEntityId() {
        return this.id;
    }

    @Override
    public String getEntityName() {
        return getClass().getSimpleName();
    }

    public Session update(String description) {
        this.description = description;
        return this;
    }
}
