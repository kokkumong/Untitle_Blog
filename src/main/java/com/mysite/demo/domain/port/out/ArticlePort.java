package com.mysite.demo.domain.port.out;

import com.mysite.demo.domain.model.Article;
import java.util.Optional;

public interface ArticlePort {
    Article save(Article article);
    Optional<Article> findById(Long articleId);
    void deleteById(Long articleId);
}
