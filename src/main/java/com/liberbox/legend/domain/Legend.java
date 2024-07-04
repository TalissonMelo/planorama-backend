package com.liberbox.legend.domain;

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
public class Legend extends ToEntity implements Auditable {

    @Id
    private String id;

    private String ownerId;

    private String color;

    private String description;

    private Legend(String ownerId, String color, String description) {
        this.id = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.color = color;
        this.description = description;

    }

    public static Legend to(String ownerId, String color, String description) {
        return new Legend(ownerId, color, description);
    }

    @Override
    public String getEntityId() {
        return this.id;
    }

    @Override
    public String getEntityName() {
        return getClass().getSimpleName();
    }

    public Legend update(String color, String description) {
        this.color = color;
        this.description = description;
        return this;
    }
}
