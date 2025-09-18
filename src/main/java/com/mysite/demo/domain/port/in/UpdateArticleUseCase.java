package com.mysite.demo.domain.port.in;

import com.example.demo.domain.model.Article;

public interface UpdateArticleUseCase {

    Article updateArticle(UpdateArticleCommand command);

    record UpdateArticleCommand(
            Long articleId,
            String email,
            String password,
            String title,
            String content
    ) {}
}
