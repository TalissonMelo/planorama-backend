package com.liberbox.members.domain;

import com.liberbox.audit.repository.Auditable;
import com.liberbox.config.domain.ToEntity;
import com.liberbox.members.domain.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Member extends ToEntity implements Auditable {

    @Id
    private String id;

    private String idSchedule;

    private String ownerId;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    private Member(String idSchedule, String ownerId, MemberType type) {
        this.id = UUID.randomUUID().toString();
        this.idSchedule = idSchedule;
        this.ownerId = ownerId;
        this.type = type;

    }

    public static Member to(String idSchedule, String ownerId, MemberType type) {
        return new Member(idSchedule, ownerId, type);
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
