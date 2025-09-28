package com.mysite.demo.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateArticleRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String title,
        @NotBlank String content
) {}