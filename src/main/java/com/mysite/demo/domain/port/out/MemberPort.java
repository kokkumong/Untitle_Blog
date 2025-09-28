package com.mysite.demo.domain.port.out;

import com.mysite.demo.domain.model.Member;
import java.util.Optional;

public interface MemberPort {
    Optional<Member> findByEmail(String email);
    Member save(Member member);
}
