package com.liberbox.members.service;

import com.liberbox.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMemberIdService {

    private final MemberRepository repository;

    public void execute(String memberId) {

        repository.deleteById(memberId);
    }

}
