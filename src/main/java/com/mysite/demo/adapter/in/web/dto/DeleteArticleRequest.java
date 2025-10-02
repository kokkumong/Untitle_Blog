package com.mysite.demo.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteArticleRequest(
        @NotBlank String email,
        @NotBlank String password
) {}
