package com.dogpamines.dogseek.redistTest;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public boolean checkAvailableMember(String memberId) {
        Member member = memberRepository.findById(memberId);
        return member != null;
    }
}
