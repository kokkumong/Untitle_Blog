package com.mysite.demo.domain.port.in;

public interface DeleteArticleUseCase {
    void deleteArticle(DeleteArticleCommand command);
    record DeleteArticleCommand(
            Long articleId,
            String email,
            String password
    ) {}
}
