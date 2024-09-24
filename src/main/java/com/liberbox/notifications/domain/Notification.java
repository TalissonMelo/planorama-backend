package com.liberbox.notifications.domain;

import com.liberbox.audit.repository.Auditable;
import com.liberbox.config.domain.ToEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Notification extends ToEntity implements Auditable {

    @Id
    private String id;

    private String ownerId;

    private String scheduleId;

    private String sessionId;

    private String receivedId;

    private String description;

    private Boolean isView = false;

    private LocalDate data = LocalDate.now();

    private Notification(String ownerId, String scheduleId, String sessionId, String receivedId, String description) {
        this.id = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.sessionId = sessionId;
        this.receivedId = receivedId;
        this.scheduleId = scheduleId;
        this.description = description;

    }

    public static Notification to(String ownerId, String scheduleId, String sessionId, String receivedId, String description) {
        return new Notification(ownerId, scheduleId, sessionId, receivedId, description);
    }

    @Override
    public String getEntityId() {
        return this.id;
    }

    @Override
    public String getEntityName() {
        return getClass().getSimpleName();
    }
}
