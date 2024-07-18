package com.liberbox.chat.domain;

import com.liberbox.audit.repository.Auditable;
import com.liberbox.config.domain.ToEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Chat extends ToEntity implements Auditable {

    @Id
    private String id;

    private String ownerId;

    private String sessionId;

    private String description;

    private Chat(String ownerId,  String sessionId, String description) {
        this.id = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.sessionId = sessionId;
        this.description = description;

    }

    public static Chat to(String ownerId, String sessionId, String description) {
        return new Chat(ownerId, sessionId, description);
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
