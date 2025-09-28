package com.mysite.demo.adapter.in.web.dto;

import com.mysite.demo.domain.model.Article;
import com.mysite.demo.domain.model.Member;

public record ArticleResponse(
        Long articleId,
        String email,
        String title,
        String content
) {
    public static ArticleResponse from(Article article, Member member) {
        return new ArticleResponse(
                article.getId(),
                member.getEmail(),
                article.getTitle(),
                article.getContent()
        );
    }
}