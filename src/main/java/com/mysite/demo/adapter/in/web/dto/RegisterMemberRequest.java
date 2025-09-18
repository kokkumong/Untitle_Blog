package com.mysite.demo.adapter.in.web.dto;
import com.mysite.demo.domain.port.in.RegisterMemberUseCase.RegisterMemberCommand;
import jakarta.validation.constraints.NotBlank;

public record RegisterMemberRequest(
        @NotBlank(message = "이메일은 필수입니다.") String email,
        @NotBlank(message = "비밀번호는 필수입니다.") String password,
        @NotBlank(message = "이름은 필수입니다.") String userName
) {
    public RegisterMemberCommand toCommand() {
        return new RegisterMemberCommand(email, password, userName);
    }
}
