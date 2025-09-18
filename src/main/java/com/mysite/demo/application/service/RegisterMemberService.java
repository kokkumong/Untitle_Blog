package com.mysite.demo.application.service;
import com.mysite.demo.domain.model.Member;
import com.mysite.demo.domain.port.in.RegisterMemberUseCase;
import com.mysite.demo.domain.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterMemberService implements RegisterMemberUseCase {
    private final MemberPort memberPort;
    @Override
    public Member registerMember(RegisterMemberCommand command){
        memberPort.findByEmail(command.email()).ifPresent(member -> {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        });

        Member newMember = Member.withoutId(
                command.email(),
                command.password(),
                command.userName()
        );
        return memberPort.save(newMember);
    }
}
