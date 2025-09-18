package com.mysite.demo.domain.port.in;

import com.mysite.demo.domain.model.Member;

public interface RegisterMemberUseCase {
    Member registerMember(RegisterMemberCommand command);
    record RegisterMemberCommand(String email, String password, String userName){
        public RegisterMemberCommand {
            if(email == null || email.isBlank()){
                throw new IllegalArgumentException("이메일은 필수 항목입니다.");
            }
            if(password == null || password.isBlank()){
                throw new IllegalArgumentException("비밀번호는 필수 항목입니다.");
            }
        }
    }
}
