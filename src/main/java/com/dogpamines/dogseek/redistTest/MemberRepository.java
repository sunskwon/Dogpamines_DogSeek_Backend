package com.dogpamines.dogseek.redistTest;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MemberRepository {

    HashMap<String, Member> members = new HashMap<>();

    public MemberRepository() {
        for(int i=0; i<10; i++){
            String id = String.valueOf(i);
            String name = "Member" + i;
            String email = "email" + i + "@test.com";
            members.put(id, new Member(id, name, email));
        }
    }

    public Member findById(String memberId) {
        if(members.containsKey(memberId)){
            return members.get(memberId);
        }else{
            return null;
        }
    }
}
