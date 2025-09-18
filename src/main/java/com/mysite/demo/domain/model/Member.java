package com.mysite.demo.domain.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private Long id;
    private String email;
    private String password;
    private String userName;

    public static Member withoutId(String email, String password, String userName) {
        return Member.builder()
                .email(email)
                .password(password)
                .userName(userName)
                .build();
    }
}
