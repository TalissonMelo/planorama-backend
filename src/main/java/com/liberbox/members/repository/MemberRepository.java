package com.liberbox.members.repository;

import com.liberbox.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("SELECT m FROM Member m WHERE m.scheduleId = :scheduleId ")
    List<Member> listMemberToScheduleId(String scheduleId);

    @Query("SELECT m FROM Member m WHERE m.scheduleId = :scheduleId AND m.ownerId = :ownerId ")
    Member listMemberToScheduleIdAndMember(String scheduleId, String ownerId);

    @Query("SELECT m FROM Member m WHERE m.scheduleId = :scheduleId AND  m.ownerId <> :ownerId")
    List<Member> listMemberToScheduleIdAndNotMember(String scheduleId, String ownerId);
}
